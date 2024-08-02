package org.emerald.apifirst.apifirstserver.mappers;

import org.emerald.apifirst.apifirstserver.domain.Order;
import org.emerald.apifirst.model.OrderCreateDto;
import org.emerald.apifirst.model.OrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDto orderToDto(Order order);

    Order orderCreateDtoToOrder(OrderCreateDto dto);
}
