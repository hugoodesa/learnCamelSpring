package br.com.stapassoli.learnCamelSpring.dto;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreateDTO {

    private String name;

    public Product toEntity() {
        return Product
                .builder()
                .name(name)
                .build();
    }

}
