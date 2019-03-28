package com.example.springboottutorial.controllers;

import com.example.springboottutorial.models.Product;
import com.example.springboottutorial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping()
    public String addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Add Product";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product oldProduct = productRepository.findById(id).orElse(null);
        oldProduct.setProductName(product.getProductName());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
        return "Update";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.delete(product);
        return "Delete Product";
    }

    @GetMapping("/test")
    public String callWebService() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:4000/products";
        String data = restTemplate.getForEntity(url, String.class).getBody();
        return data;
    }

}
