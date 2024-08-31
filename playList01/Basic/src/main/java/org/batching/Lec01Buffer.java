package org.batching;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    public static void main(String[] args) throws InterruptedException {

        eventStream()
                .buffer(5)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static Flux<String> eventStream()
    {
        return Flux.interval(Duration.ofMillis(300))
                .map(i->"event " +i) ;
    }

}
