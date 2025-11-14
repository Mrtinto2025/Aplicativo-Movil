package com.app.backend.service;

import com.app.backend.model.Subcategory;
import com.app.backend.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }

    public List<Subcategory> findByCategoryId(Long categoryId) {
        return subcategoryRepository.findByCategoryId(categoryId);
    }

    public Subcategory findById(Long id) {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrada"));
    }

    public Subcategory create(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory update(Long id, Subcategory details) {
        Subcategory subcategory = findById(id);
        subcategory.setName(details.getName());
        subcategory.setDescription(details.getDescription());
        subcategory.setActive(details.getActive());
        return subcategoryRepository.save(subcategory);
    }

    public void delete(Long id) {
        Subcategory subcategory = findById(id);
        subcategoryRepository.delete(subcategory);
    }

}