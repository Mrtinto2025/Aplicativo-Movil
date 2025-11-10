package com.app.backend.Model;

import jakarta.persistence.*;
import lomoboK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


@Data
@Entity
@Table(name = "subcatgories")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

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
}