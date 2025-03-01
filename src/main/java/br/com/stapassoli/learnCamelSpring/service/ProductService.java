package br.com.stapassoli.learnCamelSpring.service;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import br.com.stapassoli.learnCamelSpring.dto.ProductCodeDTO;
import br.com.stapassoli.learnCamelSpring.dto.ProductCreateDTO;
import br.com.stapassoli.learnCamelSpring.domain.ProductUpdateDTO;
import br.com.stapassoli.learnCamelSpring.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProducerTemplate producerTemplate;

    public void callCamelRoute(Product product) {
        producerTemplate.setDefaultEndpointUri("direct:product-route");
        producerTemplate.requestBody("direct:product-route", product);
        //ProductCodeDTO response = producerTemplate.requestBody("direct:product-route", product, ProductCodeDTO.class);
        //System.out.println(response.getCode());
    }

    public Product createProduct(ProductCreateDTO productCreateDTO) {
        Product product = this.repository.save(productCreateDTO.toEntity());
        callCamelRoute(product);
        return product;
    }

    public Product updateProduct(ProductUpdateDTO productUpdateDTO) {
        Long id = productUpdateDTO.getId();
        String code = productUpdateDTO.getCode();

        Product product = getProduct(id);
        product.setCode(code);

        return this.repository.save(product);
    }

    private Product getProduct(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not possible to find the product"));
    }

    public ProductCodeDTO generateCodeToProduct() {
        String code = UUID.randomUUID().toString();
        return ProductCodeDTO
                .builder()
                .code(code)
                .build();
    }

    /*public String generateCodeToProduct() {
        return UUID.randomUUID().toString();
    }*/

}
