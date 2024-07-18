package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> listOrders();

    Order getOrderById(UUID orderId);
}