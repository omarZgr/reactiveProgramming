package com.zougrar.assigment_trending_product_redis.cache.myTry;

import com.zougrar.assigment_trending_product_redis.cache.CacheTemplate;
import com.zougrar.assigment_trending_product_redis.entity.Product;
import com.zougrar.assigment_trending_product_redis.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RMapReactive;
import org.redisson.api.RScoredSortedSetReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@Primary
public class ProductCacheTemplateMyTry extends CacheTemplate<Integer, Product> {

    @Autowired
    private ProductRepository productRepository;

    private RMapReactive<Integer, Product> map;
    private RScoredSortedSetReactive<Integer> visitSet;

    public ProductCacheTemplateMyTry(RedissonReactiveClient client) {
        this.map = client.getMap("products", new TypedJsonJacksonCodec(Integer.class, Product.class));
        this.visitSet = client.getScoredSortedSet("products:visit:" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Override
    protected Mono<Product> getFromSource(Integer id) {
        return this.productRepository.findById(id)
                .doOnNext(product -> log.info("Retrieved productId: " + id + " from DB"));
    }

    @Override
    protected Mono<Product> getFromCache(Integer id) {
        return this.map.get(id)
                .doOnNext(product -> log.info("Retrieved productId: " + id + " from Redis cache"))
                .doOnError(e -> log.error("Error retrieving productId: " + id + " from Redis cache: " + e.getMessage()));
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

    // Method to track product visits
    public Mono<Void> trackVisit(Integer productId) {
        return this.visitSet.addScore(productId, 1.0)
                .then() // Use `.then()` to convert Mono<Double> to Mono<Void>
                .doOnSuccess(aVoid -> log.info("Tracked visit for productId: " + productId))
                .doOnError(e -> log.error("Failed to track visit for productId: " + productId, e));
    }


    // Method to get trending products
    public Flux<Map.Entry<Integer, Double>> getTrendingProducts() {
        return this.visitSet.entryRangeReversed(0, -1)
                .flatMapMany(entries -> Flux.fromIterable(entries)
                        .map(entry -> Map.entry(entry.getValue(), entry.getScore())))
                .doOnNext(entry -> log.info("Fetched trending product: " + entry))
                .doOnError(e -> log.error("Failed to fetch trending products", e));
    }
}
