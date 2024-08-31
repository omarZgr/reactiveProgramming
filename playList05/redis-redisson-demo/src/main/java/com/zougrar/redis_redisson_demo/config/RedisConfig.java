package com.zougrar.redis_redisson_demo.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public RedissonReactiveClient redissonReactiveClient()
    {
        Config config = new Config()  ;
        config.useSingleServer().setAddress("redis://127.0.0.1:6379" );
        RedissonReactiveClient reactive = Redisson.createReactive(config);
        return reactive;
    }
}
