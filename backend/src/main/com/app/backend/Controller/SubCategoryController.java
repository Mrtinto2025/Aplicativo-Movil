package com.app.backend.controller;

import com.app.backend.model.SubCategory;
import com.app.backend.service.SubCategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HTTP.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bin.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class SubCategoryController{

    @Autowired
    private SubCategoryService SubCategoryservice;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<SubCategory>> getAllCategories(){
        return ResponseEntity.ok(SubSubCategoryService.findAll());
    }

    @GetMapping("/category/{categoryid}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long categoryid){
        return ResponseEntity.ok(SubCategoryService.findById(categoryid));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(SubCategoryService.findById(id));
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory SubCategory){
        return ResponseEntity.ok(SubCategoryService.create(SubCategory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<SubCategory> updateSubSubCategory(@PathVariable Long id, @RequestBody SubCategory SubCategory){
        return ResponseEntity.ok(SubCategoryService.update(id, SubCategory));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<SubCategory> deleteSubCategory(@PathVariable Long id, @RequestBody SubCategory SubCategory){
        SubCategoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("SubCategoria eliminada con exito mi rey"));
    }

}