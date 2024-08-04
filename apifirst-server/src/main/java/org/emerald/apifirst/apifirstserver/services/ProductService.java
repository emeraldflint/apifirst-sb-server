package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.ProductCreateDto;
import org.emerald.apifirst.model.ProductDto;
import org.emerald.apifirst.model.ProductPatchDto;
import org.emerald.apifirst.model.ProductUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> listProducts();

    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductCreateDto product);

    ProductDto updateProduct(UUID productId, ProductUpdateDto productUpdateDto);

    ProductDto patchProduct(UUID productId, ProductPatchDto product);
}