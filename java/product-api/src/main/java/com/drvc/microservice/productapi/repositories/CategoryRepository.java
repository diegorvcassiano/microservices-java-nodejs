package com.drvc.microservice.productapi.repositories;

import java.util.List;

import com.drvc.microservice.productapi.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public List<Category> findByDescriptionIgnoreCaseContaining(String description);

}
