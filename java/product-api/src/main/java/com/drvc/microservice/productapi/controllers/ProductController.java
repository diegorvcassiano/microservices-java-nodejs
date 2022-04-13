package com.drvc.microservice.productapi.controllers;

import java.util.List;

import com.drvc.microservice.productapi.dtos.ProductRequest;
import com.drvc.microservice.productapi.dtos.ProductResponse;
import com.drvc.microservice.productapi.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
        return service.save(request);
    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/name/{name}")
    public List<ProductResponse> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponse> findByCategoryId(@PathVariable Integer id) {
        return service.findByCategoryId(id);
    }

    @GetMapping("/supplier/{id}")
    public List<ProductResponse> findBySupplierId(@PathVariable Integer id) {
        return service.findBySupplierId(id);
    }
}
