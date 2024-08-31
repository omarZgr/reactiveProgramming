package com.zougrar.assigment_trending_product_redis.controller;

import com.zougrar.assigment_trending_product_redis.cache.myTry.ProductCacheTemplateMyTry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("product/metrics2")
public class Trending {

    @Autowired
    private ProductCacheTemplateMyTry productCacheTemplateMyTry;

    // Endpoint to get trending products
    @GetMapping(value = "/trending",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map.Entry<Integer, Double>> getTrendingProducts() {
        return productCacheTemplateMyTry.getTrendingProducts()
                .doOnError(e -> {
                    // Log error
                    e.printStackTrace();
                });

    }

}



