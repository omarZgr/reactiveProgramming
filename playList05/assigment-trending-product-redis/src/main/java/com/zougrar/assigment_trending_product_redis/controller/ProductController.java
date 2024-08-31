package com.zougrar.assigment_trending_product_redis.controller;

import com.zougrar.assigment_trending_product_redis.entity.Product;
import com.zougrar.assigment_trending_product_redis.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    // Fetch a product by ID using Mono
    @GetMapping(value = "/{id}")
    public Mono<Product> get(@PathVariable Integer id) {
        return this.productService.get(id);
    }
    @PutMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        // Convert the Product object to Mono<Product> and call the service
        return this.productService.update(id, Mono.just(product));
    }

    // Delete a product by ID using Mono<Void>
    @DeleteMapping(value = "/{id}/delete")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.productService.delete(id);
    }
}
