package org.backPressure;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec02Drop {

    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(fluxSink ->
        {
            for (int i = 0; i < 500; i++) {
                System.out.println("pushing : " +i);
                fluxSink.next(i) ;
            }
            fluxSink.complete();
        })
                .onBackpressureDrop()
                .publishOn(Schedulers.boundedElastic())
                //.delayElements(Duration.ofNanos(5));

        ;

        flux.subscribe(sub-> System.out.println("received : "+sub));

        Util.sleepSeconds(60);

    }
}
