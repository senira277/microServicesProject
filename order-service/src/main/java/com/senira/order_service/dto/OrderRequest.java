package com.senira.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String skuCode, Integer quantity, BigDecimal price) {
    // Constructor, getters, and other methods can be generated automatically by Lombok or manually if needed
}
