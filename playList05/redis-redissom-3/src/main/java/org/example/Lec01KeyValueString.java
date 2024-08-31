package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;

public class Lec01KeyValueString {

    public static void main(String[] args) {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RBucketReactive<String> bucket = client.getBucket("key", StringCodec.INSTANCE) ;


        bucket.set("value").subscribe(i-> System.out.println("Push : "+i));

        bucket.get().subscribe(i-> System.out.println("Received : "+i)) ;
    }
}
