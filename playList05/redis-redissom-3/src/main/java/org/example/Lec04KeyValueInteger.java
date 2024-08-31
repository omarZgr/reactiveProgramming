package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04KeyValueInteger {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RAtomicLongReactive atomicLongReactive = client.getAtomicLong("visit")  ;

        atomicLongReactive.set(0)
                .thenMany(
                        Flux.range(1,30)
                                .delayElements(Duration.ofSeconds(2))
                                .doOnNext(i-> System.out.println("current value : "+i))
                                .flatMap(i->atomicLongReactive.incrementAndGet())
                ).subscribe()

        ;




        Thread.sleep(11111);

    }
}
