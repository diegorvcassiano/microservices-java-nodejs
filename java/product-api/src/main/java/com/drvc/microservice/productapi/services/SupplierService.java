package com.drvc.microservice.productapi.services;

import com.drvc.microservice.productapi.dtos.SupplierRequest;
import com.drvc.microservice.productapi.dtos.SupplierResponse;
import com.drvc.microservice.productapi.exceptions.ValidationException;
import com.drvc.microservice.productapi.models.Supplier;
import com.drvc.microservice.productapi.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public SupplierResponse save(SupplierRequest request) {
        validateSupplierNameInformed(request);
        var supplier = repository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (ObjectUtils.isEmpty(request.getName())) {
            throw new ValidationException("Supplier name not informed!");
        }
    }

    public Supplier findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Supplier not found for Id: " + id));
    }

}
