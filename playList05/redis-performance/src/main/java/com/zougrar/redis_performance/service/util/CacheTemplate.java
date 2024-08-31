package com.zougrar.redis_performance.service.util;

import reactor.core.publisher.Mono;

public abstract class CacheTemplate<Key,Entity> {

    public Mono<Entity> get(Key key)
    {
        return getFromCache(key)
                .switchIfEmpty(
                        getFromSource(key)
                                .flatMap(e->updateCache(key,e))
                );
    }

    public Mono<Entity> update(Key key,Entity entity)
    {
        return updateSource(key,entity)
                .flatMap(e->deleteFromCache(key).thenReturn(e))  ;
    }

    public Mono<Void> delete(Key key)
    {
        return deleteFromSource(key)
                .then(deleteFromCache(key)) ;
    }

    abstract protected Mono<Entity> getFromSource(Key key) ;
    abstract protected Mono<Entity> getFromCache(Key key) ;
    abstract protected Mono<Entity> updateSource(Key key,Entity entity) ;
    abstract protected Mono<Entity> updateCache(Key key,Entity entity) ;
    abstract protected Mono<Void> deleteFromSource(Key key) ;
    abstract protected Mono<Void> deleteFromCache(Key key) ;

}
