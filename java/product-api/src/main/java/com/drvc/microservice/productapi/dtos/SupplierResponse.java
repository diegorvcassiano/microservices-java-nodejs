package com.drvc.microservice.productapi.dtos;

import com.drvc.microservice.productapi.models.Supplier;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class SupplierResponse {

    private Integer id;
    private String name;

    public static SupplierResponse of(Supplier supplier) {
        var response = new SupplierResponse();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }
}
