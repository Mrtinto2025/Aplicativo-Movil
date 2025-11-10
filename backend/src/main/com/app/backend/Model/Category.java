package com.app.backend.Model;

import jakarta.persistence.*;
import lomoboK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


@Data
@Entity
@Table(name = "catgories")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "Category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subcategory> Subcategories;

    public Long getId(){
        return id;
    }

    public Long setId(){
        this.id = id;
    }

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

    public List<Subcategory> getSubcategories(){
        this.Subcategories = Subcategories;
    }

    public Void setSubcategories(List<Subcategory> Subcategories){
        this.Subcategories = Subcategories;
    }
}