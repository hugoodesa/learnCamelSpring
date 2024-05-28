package br.com.stapassoli.learnCamelSpring.routes;

import br.com.stapassoli.learnCamelSpring.domain.ProductUpdateDTO;
import br.com.stapassoli.learnCamelSpring.dto.ProductCodeDTO;
import br.com.stapassoli.learnCamelSpring.routes.processor.UpdateProductProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRouter extends RouteBuilder {

    private final String URI = "http://localhost:8080/";
    private final String GENERATE_CODE = "product";
    private final String PRODUCT_ROUTE = "direct:product-route";
    private final String PRODUCT_UPDATE_ROUTE = "direct:product-route-update";

    private final String PRODUCT_PROPERTIE = "product-saved";
    private final String PRODUCT_CODE_BODY_PROPERTIE = "product-recieved";

    private final UpdateProductProcessor updateProductProcessor;

    @Override
    public void configure() throws Exception {

        from(PRODUCT_ROUTE)
                .id(PRODUCT_ROUTE)
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setProperty(PRODUCT_PROPERTIE, body()) //product
                .log("Product : ${body}")
                .toD(URI.concat(GENERATE_CODE))
                .log("Generate code : ${body}")
                .unmarshal(new JacksonDataFormat(ProductCodeDTO.class))
                .setProperty(PRODUCT_CODE_BODY_PROPERTIE, body()) //code
                .to(PRODUCT_UPDATE_ROUTE)
                .end();

        from(PRODUCT_UPDATE_ROUTE)
                .id(PRODUCT_UPDATE_ROUTE)
                .marshal(new JacksonDataFormat(ProductCodeDTO.class))
                .process(updateProductProcessor)
                //.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.PUT))
                //.convertBodyTo(ProductUpdateDTO.class)
                //.unmarshal(new JacksonDataFormat(ProductUpdateDTO.class))
                /*.toD(URI.concat(GENERATE_CODE))
                .process(e->{
                    System.out.println(e);
                })*/
                .end();

    }

}
