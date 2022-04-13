package com.drvc.microservice.productapi.exceptions;

import lombok.Data;

@Data
public class ExceptionDetails {
    private int status;
    private String message;
}
