package com.drvc.microservice.productapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.drvc.microservice.productapi.dtos.ProductRequest;
import com.drvc.microservice.productapi.dtos.ProductResponse;
import com.drvc.microservice.productapi.dtos.ProductStockDTO;
import com.drvc.microservice.productapi.dtos.SalesConfirmationDTO;
import com.drvc.microservice.productapi.exceptions.ValidationException;
import com.drvc.microservice.productapi.models.Product;
import com.drvc.microservice.productapi.rabbitmq.SalesConfirmationSender;
import com.drvc.microservice.productapi.repositories.ProductRepository;
import com.drvc.microservice.productapi.sales.SalesStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SalesConfirmationSender salesConfirmationSender;

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());

        var product = repository.save(Product.of(request, category, supplier));
        return ProductResponse.of(product);
    }

    public ProductResponse findById(Integer id) {
        return ProductResponse.of(repository.findById(id).get());
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            throw new ValidationException("Name cannot be empty");
        }
        return repository.findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByCategoryId(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ValidationException("Category id cannot be empty");
        }
        return repository.findByCategoryId(id)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findBySupplierId(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ValidationException("Supplier id cannot be empty");
        }
        return repository.findBySupplierId(id)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    private void validateProductDataInformed(ProductRequest request) {
        if (ObjectUtils.isEmpty(request.getName())) {
            throw new ValidationException("Product name not informed!");
        }
        if (ObjectUtils.isEmpty(request.getQuantity())) {
            throw new ValidationException("Product quantity not informed!");
        }
        if (request.getQuantity() <= 0) {
            throw new ValidationException("Product quantity is incorrect. Quantity cannot be 0 or lesser!");
        }
    }

    private void validateCategoryAndSupplierInformed(ProductRequest request) {
        if (ObjectUtils.isEmpty(request.getCategoryId())) {
            throw new ValidationException("Product category id not available!");
        }
        if (ObjectUtils.isEmpty(request.getSupplierId())) {
            throw new ValidationException("Product supplier id not available!");
        }
    }

    public void updateProductStock(ProductStockDTO productDTO) {
        logger.info("updateProductStock - Received message: " + productDTO.toString());
        try {
            validateStockUpdateData(productDTO);
            // Test - Send a fake confirmation just for testing purposes
            salesConfirmationSender.salesConfirmationMessage(
                    new SalesConfirmationDTO(productDTO.getSalesId(), SalesStatus.APPROVED));
        } catch (Exception e) {
            logger.error("Error while processing sales request", e.getMessage());
            salesConfirmationSender.salesConfirmationMessage(
                    new SalesConfirmationDTO(productDTO.getSalesId(), SalesStatus.REJECTED));
        }

    }

    @Transactional
    private void validateStockUpdateData(ProductStockDTO productDTO) {
        if (ObjectUtils.isEmpty(productDTO)
                || ObjectUtils.isEmpty(productDTO.getSalesId())) {
            throw new ValidationException(("The product data and sales id must be informed!"));
        }

        if (ObjectUtils.isEmpty(productDTO.getProducts())) {
            throw new ValidationException(("The sales products cannot be empty!"));
        }

        productDTO.getProducts()
                .forEach(sales -> {
                    if (ObjectUtils.isEmpty(sales.getQuantity())
                            || ObjectUtils.isEmpty(sales.getProductId())) {
                        throw new ValidationException("For sales, product id and quantity must be informed!");
                    }
                });
    }
}
