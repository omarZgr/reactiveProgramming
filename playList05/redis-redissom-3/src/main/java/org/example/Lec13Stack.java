package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RDequeReactive;
import org.redisson.api.RQueueReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;

public class Lec13Stack {


    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RDequeReactive<Object> deque = client.getDeque("numbers-in-order", LongCodec.INSTANCE) ;

        deque.pollLast().doOnNext(i-> System.out.println("item : "+i)).repeat(4).subscribe();

        Thread.sleep(20000);



    }
}
