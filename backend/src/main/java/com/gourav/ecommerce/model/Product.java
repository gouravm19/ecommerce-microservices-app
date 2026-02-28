package com.gourav.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String category;
    @Column(length = 1000) private String description;
    @Column(nullable = false) private BigDecimal price;
    @Column(nullable = false) private Integer stock;
    private String imageUrl;
    public Product(){}
    public Product(Long id,String name,String category,String description,BigDecimal price,Integer stock,String imageUrl){this.id=id;this.name=name;this.category=category;this.description=description;this.price=price;this.stock=stock;this.imageUrl=imageUrl;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public java.math.BigDecimal getPrice(){return price;} public void setPrice(java.math.BigDecimal price){this.price=price;}
    public Integer getStock(){return stock;} public void setStock(Integer stock){this.stock=stock;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
}
