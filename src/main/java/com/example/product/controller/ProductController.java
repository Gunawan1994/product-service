package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.dto.ProductResponse;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        ProductResponse resp = productService.getProduct(id);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Validated @RequestBody ProductDTO dto) {
        ProductResponse resp = productService.createProduct(dto);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductDTO dto) {
        ProductResponse resp = productService.updateProduct(id, dto);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Success deleted");
    }
}
