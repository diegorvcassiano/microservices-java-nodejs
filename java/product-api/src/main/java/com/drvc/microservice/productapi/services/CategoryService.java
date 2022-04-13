package com.drvc.microservice.productapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.drvc.microservice.productapi.dtos.CategoryRequest;
import com.drvc.microservice.productapi.dtos.CategoryResponse;
import com.drvc.microservice.productapi.exceptions.ValidationException;
import com.drvc.microservice.productapi.models.Category;
import com.drvc.microservice.productapi.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryResponse save(CategoryRequest request) {
        validateCategoryNameInformed(request);
        var category = repository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (ObjectUtils.isEmpty(request.getDescription())) {
            throw new ValidationException("Category description not informed!");
        }
    }

    public Category findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Category not found for Id: " + id));
    }

    public CategoryResponse findByIdResponse(Integer id) {
        return CategoryResponse.of(findById(id));
    }

    public List<CategoryResponse> findDescription(String description) {
        if (ObjectUtils.isEmpty(description)) {
            throw new ValidationException("Description cannot be empty");
        }
        return repository.findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(category -> CategoryResponse.of(category))
                .collect(Collectors.toList());

    }

    public List<CategoryResponse> findAll() {
        return repository.findAll()
                .stream().map(CategoryResponse::of)
                .collect(Collectors.toList());
    }
}
