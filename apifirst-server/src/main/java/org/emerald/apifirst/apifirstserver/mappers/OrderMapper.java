package org.emerald.apifirst.apifirstserver.mappers;

import org.emerald.apifirst.apifirstserver.domain.Order;
import org.emerald.apifirst.model.OrderCreateDto;
import org.emerald.apifirst.model.OrderDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
    @Mapping(target = "shipmentInfo", ignore = true)
    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Order dtoToOrder(OrderCreateDto orderDto);

    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    Order dtoToOrder(OrderDto orderDto);

    OrderDto orderToDto(Order order);
}
