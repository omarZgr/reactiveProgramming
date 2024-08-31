package org.example;

import org.example.config.RedissonConfig;
import org.example.entity.User;
import org.redisson.api.DeletedObjectListener;
import org.redisson.api.ExpiredObjectListener;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.concurrent.TimeUnit;

public class Lec09DeletedEventListener {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RBucketReactive<String> bucket = client.getBucket("key", StringCodec.INSTANCE) ;


        bucket.set("value").subscribe(i-> System.out.println("Push : "+i));

        bucket.get().subscribe(i-> System.out.println("Received : "+i)) ;

        bucket.addListener(new DeletedObjectListener() {
            @Override
            public void onDeleted(String s) {
                System.out.println("Deleted : " +s);
            }
        }).subscribe()  ;


        Thread.sleep(20000);
    }
}
