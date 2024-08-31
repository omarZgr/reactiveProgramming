package org.example.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

public class RedissonConfig {

    public RedissonReactiveClient redissonReactiveClient()
    {
        Config config = new Config()  ;
        config.useSingleServer().setAddress("redis://127.0.0.1:6379" );
        RedissonReactiveClient reactive = Redisson.createReactive(config);
        return reactive;
    }
}
