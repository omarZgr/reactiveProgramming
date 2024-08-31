package com.zougrar.redis_spring.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // Create a configuration
        Config config = new Config();
        // Configure the Redis server address
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        // Create and return the Redisson client instance
        return Redisson.create(config);
    }

    @Bean
    public RedissonReactiveClient redissonReactiveClient() {

        return redissonClient().reactive();
    }
}
