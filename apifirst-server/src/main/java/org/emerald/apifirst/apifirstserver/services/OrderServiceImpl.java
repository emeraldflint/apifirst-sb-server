package org.emerald.apifirst.apifirstserver.services;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.domain.Order;
import org.emerald.apifirst.apifirstserver.mappers.OrderMapper;
import org.emerald.apifirst.apifirstserver.repositories.OrderRepository;
import org.emerald.apifirst.model.OrderCreateDto;
import org.emerald.apifirst.model.OrderDto;
import org.emerald.apifirst.model.OrderPatchDto;
import org.emerald.apifirst.model.OrderUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto saveNewOrder(OrderCreateDto orderCreate) {
        Order savedOrder = orderRepository.saveAndFlush(orderMapper.dtoToOrder(orderCreate));

        return orderMapper.orderToDto(savedOrder);
    }

    @Override
    public List<OrderDto> listOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(orderMapper::orderToDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        return orderMapper.orderToDto(orderRepository.findById(orderId).orElseThrow(NotFoundException::new));
    }

    @Override
    public OrderDto updateOrder(UUID orderId, OrderUpdateDto order) {
        var existingOrder = orderRepository.findById(orderId).orElseThrow();
        orderMapper.updateOrder(order, existingOrder);

        return orderMapper.orderToDto(orderRepository.save(existingOrder));
    }

    @Override
    public OrderDto patchOrder(UUID orderId, OrderPatchDto orderPatchDto) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow();
        orderMapper.patchOrder(orderPatchDto, existingOrder);
        return orderMapper.orderToDto(orderRepository.saveAndFlush(existingOrder));
    }

    @Override
    public void deleteProduct(UUID orderId) {
        orderRepository.findById(orderId).ifPresentOrElse(orderRepository::delete, () -> {
            throw new NotFoundException("Oder not found");
        });
    }
}