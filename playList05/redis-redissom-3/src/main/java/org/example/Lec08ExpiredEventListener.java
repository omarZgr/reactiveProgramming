package org.example;

import org.example.config.RedissonConfig;
import org.example.entity.User;
import org.redisson.api.ExpiredObjectListener;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.concurrent.TimeUnit;

public class Lec08ExpiredEventListener {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        User user = User.create(2,"jama zougrar","canada") ;

        RBucketReactive<User> bucket = client.getBucket("user:2",new TypedJsonJacksonCodec(User.class)) ;

        bucket.set(user,10, TimeUnit.SECONDS).subscribe() ;

        bucket.get().subscribe(System.out::println) ;

        bucket.addListener(new ExpiredObjectListener() {
            @Override
            public void onExpired(String s) {
                System.out.println("Expired : "+ s);
            }
        }).subscribe() ;

        Thread.sleep(20000);
    }
}
