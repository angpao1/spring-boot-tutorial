package com.example.springboottutorial.repositories;

import com.example.springboottutorial.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
