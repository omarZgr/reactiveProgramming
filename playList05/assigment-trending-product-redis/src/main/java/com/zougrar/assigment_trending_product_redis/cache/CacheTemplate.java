package com.zougrar.assigment_trending_product_redis.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import reactor.core.publisher.Mono;

import java.util.Objects;

public abstract class CacheTemplate<Key, Entity> {

    private final ObjectMapper objectMapper  = new ObjectMapper() ;

    public Mono<Entity> get(Key key) {
        System.out.println("Key is >>>  " + key);
        return getFromCache(key)
                .doOnNext(i -> System.out.println("Just after getFromCache() "))
                .switchIfEmpty(
                        getFromSource(key)
                                .doOnNext(i -> System.out.println("Just after getFromSource() "))
                                .flatMap(e -> updateCache(key, e))
                );
    }

    public Mono<Entity> update(Key key, Entity entity) {
        return updateSource(key, entity)
                .flatMap(e -> deleteFromCache(key).thenReturn(e));
    }

    public Mono<Void> delete(Key key) {
        return deleteFromSource(key)
                .then(deleteFromCache(key));
    }

    abstract protected Mono<Entity> getFromSource(Key key);

    abstract protected Mono<Entity> getFromCache(Key key);

    abstract protected Mono<Entity> updateSource(Key key, Entity entity);

    abstract protected Mono<Entity> updateCache(Key key, Entity entity);

    abstract protected Mono<Void> deleteFromSource(Key key);

    abstract protected Mono<Void> deleteFromCache(Key key);




}
