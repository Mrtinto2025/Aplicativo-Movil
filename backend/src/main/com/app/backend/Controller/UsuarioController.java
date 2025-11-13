package com.app.backend.controller;

import com.app.backend.model.User;
import com.app.backend.service.UserService;
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
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<User> getAllUsers(@PathVariable Long id){
        return ResponseEntity.ok(subcategoryService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subcategory){
        return ResponseEntity.ok(subcategoryService.create(subcategory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subcategory){
        return ResponseEntity.ok(subcategoryService.update(id, subcategory));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubCategory> deleteSubCategory(@PathVariable Long id, @RequestBody SubCategory subcategory){
        subcategoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("SubCategoria eliminada con exito mi rey"));
    }

}