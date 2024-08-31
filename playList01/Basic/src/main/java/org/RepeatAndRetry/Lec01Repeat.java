package org.RepeatAndRetry;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec01Repeat {

    public static void main(String[] args) {

        integerFlux()
                .repeat(2)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> integerFlux()
    {
        return Flux.range(1,3)
                .doOnSubscribe(s-> System.out.println("Subscribed"))
                .doOnComplete(()-> System.out.println("**Completed")) ;
    }
}
