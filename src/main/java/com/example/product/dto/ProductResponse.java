package com.example.product.dto;

public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private Long categoryId;
    private String categoryDesc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getCategoryDesc() { return categoryDesc; }
    public void setCategoryDesc(String categoryDesc) { this.categoryDesc = categoryDesc; }
}
