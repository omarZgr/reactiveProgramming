package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBlockingDequeReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec14MessageQueue_Producer {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis();

        RBlockingDequeReactive<Long> msgQueue = client.getBlockingDeque("message-queue", LongCodec.INSTANCE);

        producer(msgQueue) ;
    }


    public static void producer(RBlockingDequeReactive<Long> msgQueue) throws InterruptedException {

        Flux.range(1,200)
                        .delayElements(Duration.ofMillis(500))
                                .doOnNext(i-> System.out.println("Send : "+i))
                                        .flatMap(i->msgQueue.add(Long.valueOf(i)))
                                                .subscribe(); ;



        Thread.sleep(100000);
    }
}