package com.zougrar.redis_performance.contoller;

import com.zougrar.redis_performance.entity.Product;
import com.zougrar.redis_performance.service.ProductServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product/v1")
@RequiredArgsConstructor
public class ProductControllerV1 {

    private final ProductServiceV1 productServiceV1;

    @GetMapping(value = "/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Product> getId(@PathVariable int id)
    {
        return this.productServiceV1.getProduct(id) ;
    }

    @PutMapping(value = "/{id}/update",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Product> update(@PathVariable int id, @RequestBody Mono<Product> productMono )
    {
        return this.productServiceV1.update(id,productMono) ;
    }
}
