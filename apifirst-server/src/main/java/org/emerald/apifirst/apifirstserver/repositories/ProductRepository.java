package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
}
