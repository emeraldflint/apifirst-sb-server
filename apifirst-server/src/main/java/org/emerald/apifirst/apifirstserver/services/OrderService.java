package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDto> listOrders();

    OrderDto getOrderById(UUID orderId);

    OrderDto saveNewOrder(OrderDto order);
}