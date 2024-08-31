package com.zougrar.redis_performance.contoller;

import com.zougrar.redis_performance.entity.Product;
import com.zougrar.redis_performance.service.ProductServiceV1;
import com.zougrar.redis_performance.service.ProductServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product/v2")
@RequiredArgsConstructor
public class ProductControllerV2 {

    private final ProductServiceV2 productServiceV2;

    @GetMapping(value = "/{id}")
    public Mono<Product> getId(@PathVariable int id)
    {
        System.out.println("Am here ");
        return this.productServiceV2.getProduct(id) ;
    }

    @PutMapping(value = "/{id}/update",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Product> update(@PathVariable int id, @RequestBody Mono<Product> productMono )
    {
        return this.productServiceV2.update(id,productMono) ;
    }
}
