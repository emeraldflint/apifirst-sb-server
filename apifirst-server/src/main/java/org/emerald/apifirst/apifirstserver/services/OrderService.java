package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.OrderCreateDto;
import org.emerald.apifirst.model.OrderDto;
import org.emerald.apifirst.model.OrderPatchDto;
import org.emerald.apifirst.model.OrderUpdateDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDto> listOrders();

    OrderDto getOrderById(UUID orderId);

    OrderDto saveNewOrder(OrderCreateDto order);

    OrderDto updateOrder(UUID orderId, OrderUpdateDto order);

    OrderDto patchOrder(UUID orderId, OrderPatchDto orderPatchDto);

    void deleteProduct(UUID orderId);
}