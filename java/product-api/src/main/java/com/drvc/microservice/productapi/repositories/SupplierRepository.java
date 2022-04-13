package com.drvc.microservice.productapi.repositories;

import java.util.List;

import com.drvc.microservice.productapi.models.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    public List<Supplier> findByNameIgnoreCaseContaining(String name);
}
