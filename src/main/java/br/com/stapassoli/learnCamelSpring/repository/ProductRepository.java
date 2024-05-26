package br.com.stapassoli.learnCamelSpring.repository;

import br.com.stapassoli.learnCamelSpring.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
