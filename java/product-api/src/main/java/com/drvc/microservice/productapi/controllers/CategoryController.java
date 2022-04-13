package com.drvc.microservice.productapi.controllers;

import java.util.List;

import com.drvc.microservice.productapi.dtos.CategoryRequest;
import com.drvc.microservice.productapi.dtos.CategoryResponse;
import com.drvc.microservice.productapi.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return service.save(request);
    }

    @GetMapping("{id}")
    public CategoryResponse findById(@PathVariable Integer id) {
        return service.findByIdResponse(id);
    }

    @GetMapping("/description/{description}")
    public List<CategoryResponse> findDescription(@PathVariable String description) {
        return service.findDescription(description);
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return service.findAll();
    }
}
