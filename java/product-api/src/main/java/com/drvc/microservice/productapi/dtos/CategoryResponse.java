package com.drvc.microservice.productapi.dtos;

import com.drvc.microservice.productapi.models.Category;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class CategoryResponse {

    private Integer id;
    private String description;

    public static CategoryResponse of(Category category) {
        var response = new CategoryResponse();
        BeanUtils.copyProperties(category, response);
        return response;
    }
}
