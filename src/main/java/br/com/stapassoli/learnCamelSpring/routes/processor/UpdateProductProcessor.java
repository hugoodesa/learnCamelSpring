package br.com.stapassoli.learnCamelSpring.routes.processor;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import br.com.stapassoli.learnCamelSpring.domain.ProductUpdateDTO;
import br.com.stapassoli.learnCamelSpring.dto.ProductCodeDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String PRODUCT_PROPERTIE = "product-saved";
        Product product = exchange.getProperty(PRODUCT_PROPERTIE, Product.class);

        String PRODUCT_CODE_BODY_PROPERTIE = "product-recieved";
        ProductCodeDTO productCode = exchange.getProperty(PRODUCT_CODE_BODY_PROPERTIE, ProductCodeDTO.class);

        // Log the properties to debug
        System.out.println("Exchange: " + exchange);
        System.out.println("Original Product (product-saved): " + product);
        System.out.println("Generated Code (product-recieved): " + productCode);

        ProductUpdateDTO productUpdateDTO = ProductUpdateDTO
                .builder()
                .code(productCode.getCode())
                .id(product.getId())
                .build();

        //ProductUpdateDTO productUpdateDTO1 = new ProductUpdateDTO();

        exchange.getIn().setBody(productUpdateDTO, ProductUpdateDTO.class);
        //exchange.getIn().setBody(productUpdateDTO1, ProductUpdateDTO.class);

    }

}
