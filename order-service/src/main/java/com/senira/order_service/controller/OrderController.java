package com.senira.order_service.controller;

import com.senira.order_service.OrderServiceApplication;
import com.senira.order_service.dto.OrderRequest;
import com.senira.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @PostMapping("/api/order")
    @ResponseStatus(HttpStatus.CREATED)

    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        log.info("Received order request: {}", orderRequest);
        orderService.placeOrder(orderRequest);

        return "Order placed successfully";
    }
}
