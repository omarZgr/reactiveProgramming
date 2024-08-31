package org.Thread.chatGPTExemple;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubsribeOn {

    public static void main(String[] args) throws InterruptedException {

        Flux<Integer> flux = Flux.range(1, 5)
                .map(i -> {
                    System.out.println("Mapping " + i + " on thread " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(Schedulers.boundedElastic());

        flux.subscribe(i -> System.out.println("Consuming " + i + " on thread " + Thread.currentThread().getName()));


        Thread.sleep(6000);
    }
}
