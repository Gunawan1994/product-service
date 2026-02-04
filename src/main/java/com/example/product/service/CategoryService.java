package com.example.product.service;

import com.example.product.dto.CategoryDTO;
import com.example.product.dto.CategoryRequest;
import com.example.product.dto.CategoryProductResponse;
import com.example.product.model.Category;
import com.example.product.model.Product;
import com.example.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> listCategories() {
        return categoryRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO createCategory(CategoryRequest req) {
        Category category = new Category();
        category.setName(req.getName());
        category = categoryRepository.save(category);
        return toDTO(category);
    }

    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        if (category.getProducts() != null) {
            dto.setProducts(category.getProducts().stream().map(this::toProductResp).collect(Collectors.toList()));
        }
        return dto;
    }

    private CategoryProductResponse toProductResp(Product product) {
        CategoryProductResponse resp = new CategoryProductResponse();
        resp.setId(product.getId());
        resp.setCode(product.getCode());
        resp.setName(product.getName());
        resp.setPrice(product.getPrice());
        resp.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        return resp;
    }
}
