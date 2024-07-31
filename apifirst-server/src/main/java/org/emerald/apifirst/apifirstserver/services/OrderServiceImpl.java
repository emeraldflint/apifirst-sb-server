package org.emerald.apifirst.apifirstserver.services;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.repositories.OrderRepository;
import org.emerald.apifirst.model.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> listOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    @Override
    public OrderDto saveNewOrder(OrderDto order) {
        return orderRepository.save(order);
    }
}