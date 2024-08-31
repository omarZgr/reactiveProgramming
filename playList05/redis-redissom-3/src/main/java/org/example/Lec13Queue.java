package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RQueueReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;

public class Lec13Queue {


    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RQueueReactive<Object> queue = client.getQueue("numbers-in-order", LongCodec.INSTANCE) ;

        queue.poll().doOnNext(i-> System.out.println("item : "+i)).repeat(6).subscribe();

        Thread.sleep(20000);



    }
}
