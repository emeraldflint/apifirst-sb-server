package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.model.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderDto, UUID> {
}