package org.emerald.apifirst.apifirstserver.controllers;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.services.ProductService;
import org.emerald.apifirst.model.ProductCreateDto;
import org.emerald.apifirst.model.ProductDto;
import org.emerald.apifirst.model.ProductPatchDto;
import org.emerald.apifirst.model.ProductUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.emerald.apifirst.apifirstserver.controllers.ProductController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/v1/products";

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<Void> saveNewProduct(@RequestBody ProductCreateDto product) {
        var createdProduct = productService.saveNewProduct(product);
        var uriComponents = UriComponentsBuilder.fromPath(BASE_URL + "/{product_id}")
                .buildAndExpand(createdProduct.getId());
        return ResponseEntity.created(URI.create(uriComponents.getPath())).build();

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID productId, @RequestBody ProductUpdateDto product) {
        var updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("productId") UUID productId,
                                                   @RequestBody ProductPatchDto product){
        ProductDto savedProduct = productService.patchProduct(productId, product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable("productId") UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}