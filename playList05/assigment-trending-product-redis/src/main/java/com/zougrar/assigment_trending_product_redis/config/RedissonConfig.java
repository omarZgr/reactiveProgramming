package com.zougrar.assigment_trending_product_redis.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient()
    {
        Config config = new Config()  ;

        config.useSingleServer()

                .setAddress("redis://127.0.0.1:6379");

        return Redisson.create(config) ;
    }

    @Bean
    public RedissonReactiveClient redissonReactiveClient()
    {
        return redissonClient().reactive() ;
    }
}
