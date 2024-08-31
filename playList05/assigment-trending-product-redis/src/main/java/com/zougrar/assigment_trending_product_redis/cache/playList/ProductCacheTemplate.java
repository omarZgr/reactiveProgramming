package com.zougrar.assigment_trending_product_redis.cache.playList;

import com.zougrar.assigment_trending_product_redis.cache.CacheTemplate;
import com.zougrar.assigment_trending_product_redis.entity.Product;
import com.zougrar.assigment_trending_product_redis.repository.ProductRepository;
import com.zougrar.assigment_trending_product_redis.service.playlist.ProductVisitService;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class ProductCacheTemplate extends CacheTemplate<Integer, Product> {

    @Autowired
    private ProductRepository productRepository;

    private RMapReactive<Integer, Product> map;


    @Autowired
    private ProductVisitService visitService ;

    public ProductCacheTemplate(RedissonReactiveClient client) {
        this.map = client.getMap("products", new TypedJsonJacksonCodec(Integer.class, Product.class));
    }

    @Override
    protected Mono<Product> getFromSource(Integer id) {
        return this.productRepository.findById(id)
                .doOnNext(product -> log.info("Retrieved productId: " + id + " from DB"))
                .doFirst(()->this.visitService.addVisit(id));
    }

    @Override
    protected Mono<Product> getFromCache(Integer id) {
        return this.map.get(id)
                .doOnNext(product -> log.info("Retrieved productId: " + id + " from Redis cache"))
                .doOnError(e -> log.error("Error retrieving productId: " + id + " from Redis cache: " + e.getMessage()))
               ;
    }

    @Override
    protected Mono<Product> updateSource(Integer id, Product product) {
        return this.productRepository.findById(id)
                .doOnNext(p -> product.setId(id))
                .flatMap(p -> this.productRepository.save(product));
    }

    @Override
    protected Mono<Product> updateCache(Integer id, Product product) {
        log.info("Updating Redis cache with productId: " + id + " and product: " + product);
        return this.map.put(id, product)
                .doOnSuccess(updated -> log.info("Cache updated successfully for productId: " + id))
                .doOnError(e -> log.error("Failed to update cache for productId: " + id, e))
                .thenReturn(product);
    }

    @Override
    protected Mono<Void> deleteFromSource(Integer id) {
        return this.productRepository.deleteById(id);
    }

    @Override
    protected Mono<Void> deleteFromCache(Integer id) {
        return this.map.fastRemove(id).then();
    }
}
