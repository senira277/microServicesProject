package com.senira.order_service.service;

import com.senira.order_service.client.InventoryClient;
import com.senira.order_service.dto.OrderRequest;
import com.senira.order_service.model.Order;
import com.senira.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public void placeOrder(OrderRequest orderRequest){
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isInStock){
            //map orderRequest to order obj
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());

            //save order to orderRepository
            orderRepository.save(order);
        }else{
            throw new RuntimeException("product with skucode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
