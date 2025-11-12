package com.app.backend.service;


import com.app.backend.model.SubCategory;
import com.app.backend.model.Category;
import com.app.backend.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class SubCategoryService{

    @Autowired
    private SubCategoryService subCategoryRepository;

    @Autowired
    private CategoryService categoryService;

    public List<SubCategory> findAll(){
        return subCategoryRepository.findAll();
    }

    public List<SubCategory> findby(){
        return subCategoryRepository.findAll();
    }

    
    public Subcategory findByCategoryId(Long id){
        return subCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("SubCategoria no encontrada papi mira bien"));
    }

    public SubCategory create(SubCategory subCategory){
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory update(Long id, SubCategory subCategoryDetails){
        SubCategory subCategory = findById(id);
        subCategory.setName(subCategoryDetails.getName());
        subCategory.setDescription(subCategoryDetails.getDescription());
        subCategory.setActive(subCategoryDetails.getActive());
        return subCategoryRepository.save(subCategory);
    }   

        public void delete(Long id){
        SubCategory subcategory = findById(id);
        subCategoryRepository.delete(subcategory);
    }
}