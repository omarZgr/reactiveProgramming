package org.Thread.chatGPTExemple;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.xml.crypto.Data;

public class PublishOn {

    public static void main(String[] args) throws InterruptedException {


        Flux<Integer> flux = Flux.range(1, 5)
                .map(i -> {
                    System.out.println("Mapping " + i + " on thread " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    System.out.println("Mapping after publishOn " + i + " on thread " + Thread.currentThread().getName());
                    return i;
                });

        flux.subscribe(i -> System.out.println("Consuming " + i + " on thread " + Thread.currentThread().getName()));
        Thread.sleep(6000);

    }
}
