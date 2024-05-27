package br.com.stapassoli.learnCamelSpring.routes.processor;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import br.com.stapassoli.learnCamelSpring.dto.ProductCodeDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductProcessor implements Processor {

    private final String PRODUCT_PROPERTIE = "product-saved";
    private final String PRODUCT_CODE_BODY_PROPERTIE = "product-recieved";
    private final String PRODUCT_PROPERTIE_NEW= "product-saved-new";

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getProperties().forEach((key, value) -> {
            System.out.println("Property Key: " + key + ", Property Value: " + value);
        });

        Product product = exchange.getProperty(PRODUCT_PROPERTIE, Product.class);
        ProductCodeDTO productCode = exchange.getProperty(PRODUCT_CODE_BODY_PROPERTIE, ProductCodeDTO.class);
        ProductCodeDTO productNew = exchange.getProperty(PRODUCT_PROPERTIE_NEW, ProductCodeDTO.class);

        // Log the properties to debug
        System.out.println("Exchange: " + exchange);
        System.out.println("Original Product (product-saved): " + product);
        System.out.println("Generated Code (product-recieved): " + productCode);
        System.out.println("Updated Product (product-saved-new): " + productNew);
    }

}
