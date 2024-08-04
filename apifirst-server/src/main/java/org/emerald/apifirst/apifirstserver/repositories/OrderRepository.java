package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.apifirstserver.domain.Customer;
import org.emerald.apifirst.apifirstserver.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByCustomer(Customer customer);
}