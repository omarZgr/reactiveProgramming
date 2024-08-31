package com.zougrar.redis_performance.service;


import com.zougrar.redis_performance.entity.Product;
import com.zougrar.redis_performance.repository.ProductRepository;
import com.zougrar.redis_performance.service.util.CacheTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceV2 {

    private final CacheTemplate<Integer,Product> cacheTemplate ;


    // Get
    public Mono<Product> getProduct(int id)
    {
        return this.cacheTemplate.get(id) ;
    }


    // Put
    public Mono<Product> update(int id,Mono<Product> productMono)
    {
        return productMono
                .flatMap(p->this.cacheTemplate.update(id,p));
    }

    // Delete
    public Mono<Void> delete(int id)
    {
        return this.cacheTemplate.delete(id) ;
    }
}
