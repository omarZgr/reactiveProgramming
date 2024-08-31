package org.Thread;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec05_FullExemple {


    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    printCurrentThread("create ");
                    for (int i = 0; i < 7; i++) {
                        printCurrentThread("emitted data " + i);
                        fluxSink.next(i);
                    }
                })
                // This will ensure that the creation and emission of data happen on boundedElastic
                .subscribeOn(Schedulers.boundedElastic())

                // This will ensure that the received data is processed on the parallel scheduler
                .publishOn(Schedulers.parallel());

        flux.subscribe(sub -> printCurrentThread("received data: " + sub));

        Thread.sleep(10000);
    }

    private static void printCurrentThread(String msg) {
        System.out.println("Thread : " + Thread.currentThread().getName() + " - Message : " + msg);
    }
}
