package com.example.rr.controller;

import com.example.rr.dto.CategoryResponseDTO;
import com.example.rr.dto.ProductResponseDTO;
import com.example.rr.entity.Category;
import com.example.rr.repository.CategoryRepository;
import com.example.rr.service.CategoryService;
import com.example.rr.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories =  categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }


}
