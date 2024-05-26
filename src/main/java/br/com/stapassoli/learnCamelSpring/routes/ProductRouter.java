package br.com.stapassoli.learnCamelSpring.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class ProductRouter extends RouteBuilder {

    private final String PRODUCT_ROUTE = "direct:product-route";

    private final String URI = "http://localhost:8080/";
    private final String GENERATE_CODE = "product";

    private final String PRODUCT_RECIEVED = "product-recieved";


    @Override
    public void configure() throws Exception {
        from(PRODUCT_ROUTE)
                .id(PRODUCT_ROUTE)
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setProperty(PRODUCT_RECIEVED,body())
                .toD(URI.concat(GENERATE_CODE))
                .process(exchange -> {
                    System.out.println("==== CAMEL ====");
                    System.out.println(exchange.getIn().getBody());
                    System.out.println("==== END CAMEL ====");

                    String body = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(body.toString());
                })
                .log("${body}")
                //.unmarshal(new JacksonDataFormat(String.class))
                .end();
    }

}
