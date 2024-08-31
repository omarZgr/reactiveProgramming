package com.zougrar.redis_spring.cityApp.service;


import com.zougrar.redis_spring.cityApp.client.CityClient;
import com.zougrar.redis_spring.cityApp.dto.City;
import org.redisson.api.RMap;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CityService {

    public RMapReactive<String, City> cityMap ;
    private CityClient  cityClient ;


    public CityService(RedissonReactiveClient client)
    {
        this.cityMap = client.getMap("city",new TypedJsonJacksonCodec(String.class,City.class)) ;
    }
    /*
            get from cache
            if empty - get from db / source
                        put it in cache
            return
     */

    public Mono<City> getCity(final String zipCode)
    {
        return this.cityMap.get(zipCode)
                .switchIfEmpty(
                        this.cityClient.getCity(zipCode)
                                .flatMap(c -> this.cityMap.fastPut(zipCode,c).thenReturn(c))
                );
    }
}
