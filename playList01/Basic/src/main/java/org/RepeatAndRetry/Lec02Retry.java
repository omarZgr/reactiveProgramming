package org.RepeatAndRetry;

import org.courseUtil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Lec02Retry {

    public static void main(String[] args) {

        integerFlux()
                .retry(2)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> integerFlux()
    {
        return Flux.range(1,3)
                .doOnSubscribe(s-> System.out.println("Subscribed"))
                .doOnComplete(()-> System.out.println("**Completed"))
                .map(i->i/0)
                .doOnError(s-> System.out.println("Error : "+s));
    }
}
