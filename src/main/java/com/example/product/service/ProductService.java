package com.example.product.service;

import com.example.product.dto.ProductDTO;
import com.example.product.dto.ProductResponse;
import com.example.product.model.Product;
import com.example.product.model.Category;
import com.example.product.repository.ProductRepository;
import com.example.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductResponse> listProducts() {
        return productRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id) {
        return productRepository.findById(id).map(this::toResponse).orElse(null);
    }

    @Transactional
    public ProductResponse createProduct(ProductDTO dto) {
        String code = dto.getCode();
        if (code == null || code.isEmpty()) {
            code = generateProductCode();
        } else if (productRepository.existsByCode(code)) {
            code = incrementProductCode(code);
        }
        Product product = new Product();
        product.setCode(code);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            product.setCategory(category);
        }
        product = productRepository.save(product);
        return toResponse(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductDTO dto) {
        Optional<Product> opt = productRepository.findById(id);
        if (!opt.isPresent()) return null;
        Product product = opt.get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            product.setCategory(category);
        }
        product = productRepository.save(product);
        return toResponse(product);
    }

    private String generateProductCode() {
        String prefix = "prd-";
        Product last = productRepository.findTopByCodeStartingWithOrderByCodeDesc(prefix);
        int nextNum = 1;
        if (last != null) {
            String lastCode = last.getCode();
            String numStr = lastCode.replace(prefix, "");
            try {
                nextNum = Integer.parseInt(numStr) + 1;
            } catch (NumberFormatException ignored) {}
        }
        return prefix + nextNum;
    }

    private String incrementProductCode(String code) {
        String prefix = code.replaceAll("\\d+$", "");
        String numStr = code.replace(prefix, "");
        int num = 1;
        try {
            num = Integer.parseInt(numStr) + 1;
        } catch (NumberFormatException ignored) {}
        return prefix + num;
    }

    private ProductResponse toResponse(Product product) {
        ProductResponse resp = new ProductResponse();
        resp.setId(product.getId());
        resp.setCode(product.getCode());
        resp.setName(product.getName());
        resp.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            resp.setCategoryId(product.getCategory().getId());
            resp.setCategoryDesc(product.getCategory().getName());
        }
        return resp;
    }
}
