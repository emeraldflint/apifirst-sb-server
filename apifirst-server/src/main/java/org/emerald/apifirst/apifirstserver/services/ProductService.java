package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> listProducts();

    Product getProductById(UUID productId);
}