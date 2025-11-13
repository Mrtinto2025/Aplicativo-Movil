package com.app.backend.Service;

import com.app.backend.respository.user.UserRepository;
import com.app.backend.repository.category.CategoryRepository;
import org.app.backend.repository.subcategory.SubcategoryRepository;
import com.app.backend.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service

public class StatsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Map<Strign, Long> getStats(){
        Map<String, Long> stats = new HashMap<>();
        stats.put("users",userRepository.count());
        stats.put("categories",categoryRepository.count());
        stats.put("subcategories",subcategoryRepository.count());
        stats.put("products",productRepository.count()); 
        return stats;   
        }+
}