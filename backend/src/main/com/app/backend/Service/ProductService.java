package com.app.backend.service;

import com.app.backend.model.Product;
import com.app.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByCategory(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public List<Product> findBySubcategoryId(Long id) {
        return productRepository.findBySubcategoryId(id);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}