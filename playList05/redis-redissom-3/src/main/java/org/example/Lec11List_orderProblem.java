package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RListReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;

public class Lec11List_orderProblem {


    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RListReactive<Long> list = client.getList("numbers", LongCodec.INSTANCE) ;

        Flux.range(1,10)
                .map(Long::valueOf)
                .flatMap(list::add)
                .doOnNext(i-> System.out.println("add : " + i))
                .subscribe();


        Thread.sleep(20000);



    }
}
