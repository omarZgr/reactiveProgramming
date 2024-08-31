package org.backPressure;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03Latest {

    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(fluxSink ->
        {
            for (int i = 0; i < 201; i++) {
                System.out.println("pushing : " +i);
                fluxSink.next(i) ;
                try {
                    Thread.sleep(1 );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            fluxSink.complete();
        })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i->
                {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }                 });

        ;

        flux.subscribe(sub-> System.out.println("received : "+sub));

        Util.sleepSeconds(60);

    }
}
