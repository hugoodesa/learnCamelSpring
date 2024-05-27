package br.com.stapassoli.learnCamelSpring.routes;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import br.com.stapassoli.learnCamelSpring.dto.ProductCodeDTO;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class ProductRouter extends RouteBuilder {

    private final String URI = "http://localhost:8080/";
    private final String GENERATE_CODE = "product";
    private final String PRODUCT_ROUTE = "direct:product-route";
    private final String PRODUCT_RECIEVED_PROPERTIE = "product-recieved";


    @Override
    public void configure() throws Exception {
        from(PRODUCT_ROUTE)
                .id(PRODUCT_ROUTE)
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setProperty(PRODUCT_RECIEVED_PROPERTIE, body())
                .toD(URI.concat(GENERATE_CODE))
                .unmarshal(new JacksonDataFormat(ProductCodeDTO.class))
                .log("${body}")
                .end();
    }

}
