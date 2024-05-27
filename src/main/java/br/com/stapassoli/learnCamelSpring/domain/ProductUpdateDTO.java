package br.com.stapassoli.learnCamelSpring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductUpdateDTO {

    private Long id;
    private String code;

}
