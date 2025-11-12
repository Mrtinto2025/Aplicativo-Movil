package com.app.backend.Service;

import com.app.backend.models.Product;
import com.app.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService{
    @Autowired
    private ProductService productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findByCategoryId(Long id){
        return product
    }
}