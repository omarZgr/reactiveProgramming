package org.example.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

public class RedissonConfig {

    public  static RedissonReactiveClient configReactiveRedis()
    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        // Create Redisson client
        return  Redisson.create(config).reactive();
    }

    public  static RedissonClient configRedis()
    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        // Create Redisson client
        return  Redisson.create(config);
    }


}
