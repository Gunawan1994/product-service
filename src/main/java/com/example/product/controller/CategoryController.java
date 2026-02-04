package com.example.product.controller;

import com.example.product.dto.CategoryDTO;
import com.example.product.dto.CategoryRequest;
import com.example.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Validated @RequestBody CategoryRequest req) {
        CategoryDTO dto = categoryService.createCategory(req);
        return ResponseEntity.ok(dto);
    }
}
