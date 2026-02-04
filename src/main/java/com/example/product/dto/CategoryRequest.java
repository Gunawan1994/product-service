package com.example.product.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
