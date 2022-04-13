package com.drvc.microservice.productapi.rabbitmq;

import com.drvc.microservice.productapi.dtos.ProductStockDTO;
import com.drvc.microservice.productapi.services.ProductService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStockListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void updateProductStock(ProductStockDTO product) {
        productService.updateProductStock(product);
    }
}
