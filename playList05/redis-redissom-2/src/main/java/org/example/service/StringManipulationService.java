package org.example.service;

import org.example.config.RedissonConfig;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;

public class StringManipulationService {

    private static RedissonReactiveClient redissonClient;

    public static void main(String[] args) {

        RedissonConfig redissonConfig = new RedissonConfig() ;

        redissonConfig.redissonReactiveClient() ;

        String key = "user" ;
        String value = "omar" ;

        RBucketReactive<String> bucket =redissonClient.getBucket(key) ;

        bucket.get() ;
    }

}
