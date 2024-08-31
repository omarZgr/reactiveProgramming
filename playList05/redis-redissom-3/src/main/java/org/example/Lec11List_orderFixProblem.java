package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RListReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Lec11List_orderFixProblem {


    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RListReactive<Long> list = client.getList("numbers-in-order", LongCodec.INSTANCE) ;

        List<Long> listJava = LongStream.range(1,10).boxed().collect(Collectors.toList());

        list.addAll(listJava).doOnNext(i-> System.out.println("add : "+ i)).subscribe() ;

        Thread.sleep(20000);



    }
}
