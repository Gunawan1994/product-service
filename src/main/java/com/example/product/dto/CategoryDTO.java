package com.example.product.dto;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private List<CategoryProductResponse> products;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public java.util.List<CategoryProductResponse> getProducts() { return products; }
    public void setProducts(java.util.List<CategoryProductResponse> products) { this.products = products; }
}
