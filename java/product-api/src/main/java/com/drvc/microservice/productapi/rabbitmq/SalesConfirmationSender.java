package com.drvc.microservice.productapi.rabbitmq;

import com.drvc.microservice.productapi.dtos.SalesConfirmationDTO;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SalesConfirmationSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;

    @Value("${app-config.rabbit.routingkey.sales-confirmation}")
    private String salesConfirmationRoutingKey;

    public void salesConfirmationMessage(SalesConfirmationDTO message) {
        try {
            log.info("salesConfirmationMessage - Sending message: " + message.toString());
            rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmationRoutingKey, message);
            log.info("Message sent!");
        } catch (Exception e) {
            log.error("salesConfirmationMessage - Error on sending message. Error: " + e.getMessage());
        }
    }
}
