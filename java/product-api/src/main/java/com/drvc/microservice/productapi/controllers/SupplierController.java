package com.drvc.microservice.productapi.controllers;

import com.drvc.microservice.productapi.dtos.SupplierRequest;
import com.drvc.microservice.productapi.dtos.SupplierResponse;
import com.drvc.microservice.productapi.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request) {
        return service.save(request);
    }

}
