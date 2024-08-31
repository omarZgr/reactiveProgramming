package org.example;

import org.example.config.RedissonConfig;
import org.example.entity.User;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;

public class Lec02KeyValueObject {

    public static void main(String[] args) {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

       User user = User.create(1,"omar zougrar","morocco") ;

        RBucketReactive<User> bucket = client.getBucket("user:1",new TypedJsonJacksonCodec(User.class)) ;

        bucket.set(user).subscribe() ;

        bucket.get().subscribe(System.out::println) ;
    }
}
