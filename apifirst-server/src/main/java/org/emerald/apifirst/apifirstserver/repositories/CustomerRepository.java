package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
