package org.backPressure;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Demo {

    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(fluxSink ->
        {
            for (int i = 0; i < 50; i++) {
                System.out.println("pushing : " +i);
                fluxSink.next(i) ;
            }
        }).delayElements(Duration.ofMillis(10));

        flux.subscribe(sub-> System.out.println("received : "+sub));

        Util.sleepSeconds(60);

    }
}
