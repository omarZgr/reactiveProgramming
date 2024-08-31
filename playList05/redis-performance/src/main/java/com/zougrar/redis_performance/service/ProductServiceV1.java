package com.zougrar.redis_performance.service;


import com.zougrar.redis_performance.entity.Product;
import com.zougrar.redis_performance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceV1 {

    private final ProductRepository productRepository ;

    public Mono<Product> getProduct(int id)
    {
        return this.productRepository.findById(id) ;
    }

    public Mono<Product> update(int id,Mono<Product> productMono)
    {
        return this.productRepository.findById(id)
                .flatMap(p->productMono.doOnNext(product -> product.setId(id)))
                .flatMap(this.productRepository::save) ;
    }
}
