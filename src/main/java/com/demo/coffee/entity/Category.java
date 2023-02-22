package com.demo.coffee.entity;

import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", unique = true)
    private Integer categoryID;
    
    @Column(name = "category_name")
    @NotNull
    @NotBlank(message = "Category's name cannot be null")
    @Size(min = 1, max = 300)
    private String categoryName;
    
    private String description;
    
    public Category(){}
    public Category(Integer categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
