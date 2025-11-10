package com.app.backend.Model;

import jakarta.persistence.*;
import lomoboK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


@Data
@Entity
@Table(name = "products")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    private Integer stock;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    public Long getname(){
        this.name = name;
    }

    public Long setname(){
        this.name = name;
    }

    public String getdescription(){
        this.description = description;
    }

    public Void setdescription(String description){
        this.description = description;
    }

    public Double getprice(){
        this.price = price;
    }

    public Void setprice(Double price){
        this.price = price;
    }

    public Integer getstock(){
        return  stock;
    }

    public Void setstock(Integer stock){
        this.stock = stock;
    }

    public Boolean getactive(){
        this.active = active;
    }

    public Void setactive(Boolean active){
        this.active = active;
    }

    public Category getCategory(){
        this.Subcategories = Subcategories;
    }

    public Void setCategory(List<Subcategory> Subcategories){
        this.category= category;
    }

    public Subcategory getSubcategory(){
        return Subcategories ;
    }

    public Void setSubcategory(List<Subcategory> Subcategories){
        this.subcategory= subcategory;
    }
}