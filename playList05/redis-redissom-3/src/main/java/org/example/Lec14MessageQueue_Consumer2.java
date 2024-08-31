package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBlockingDequeReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec14MessageQueue_Consumer2 {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis();

        RBlockingDequeReactive<Long> msgQueue = client.getBlockingDeque("message-queue", LongCodec.INSTANCE);

        consumer2(msgQueue);
    }


    public static void consumer2(RBlockingDequeReactive<Long> msgQueue) throws InterruptedException {
        msgQueue.takeElements()
                .doOnNext(i -> System.out.println("Consumer 2 : " + i))
                .doOnError(System.out::println)
                .subscribe();

        Thread.sleep(100000);
    }


}