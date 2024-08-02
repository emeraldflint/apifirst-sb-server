package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.apifirstserver.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
