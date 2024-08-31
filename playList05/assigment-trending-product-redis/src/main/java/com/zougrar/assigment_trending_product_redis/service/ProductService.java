package com.zougrar.assigment_trending_product_redis.service;


import com.zougrar.assigment_trending_product_redis.cache.CacheTemplate;
import com.zougrar.assigment_trending_product_redis.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {

    private final CacheTemplate<Integer, Product> cacheTemplate;

    public Mono<Product> get(Integer id)
    {
        return this.cacheTemplate.get(id) ;
    }

    public Mono<Product> update(Integer id, Mono<Product> productMono)
    {
        return productMono.flatMap(product -> this.cacheTemplate.update(id,product));
    }

    public Mono<Void> delete(Integer id)
    {
        return this.cacheTemplate.delete(id);
    }


}
