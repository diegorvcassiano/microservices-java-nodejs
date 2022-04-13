package com.drvc.microservice.productapi.dtos;

import com.drvc.microservice.productapi.sales.SalesStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesConfirmationDTO {
    private String salesId;
    private SalesStatus status;
}
