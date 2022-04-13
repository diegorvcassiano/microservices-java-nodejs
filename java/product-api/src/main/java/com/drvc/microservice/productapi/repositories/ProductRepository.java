package com.drvc.microservice.productapi.repositories;

import java.util.List;

import com.drvc.microservice.productapi.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findByNameIgnoreCaseContaining(String name);

    public List<Product> findByCategoryId(Integer id);

    public List<Product> findBySupplierId(Integer id);
}
