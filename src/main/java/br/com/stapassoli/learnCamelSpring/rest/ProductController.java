package br.com.stapassoli.learnCamelSpring.rest;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import br.com.stapassoli.learnCamelSpring.dto.ProductCodeDTO;
import br.com.stapassoli.learnCamelSpring.dto.ProductCreateDTO;
import br.com.stapassoli.learnCamelSpring.dto.ProductUpdateDTO;
import br.com.stapassoli.learnCamelSpring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(this.service.createProduct(productCreateDTO));
    }

    @GetMapping
    public ResponseEntity<ProductCodeDTO> generateCode() {
        return ResponseEntity.ok(this.service.generateCodeToProduct());
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductUpdateDTO productUpdateDTO) {
        return ResponseEntity.ok(this.service.updateProduct(productUpdateDTO));
    }

}
