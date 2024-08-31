package org.RepeatAndRetry;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03RetryWhen {

    public static void main(String[] args) throws InterruptedException {

        integerFlux()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(2)))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static Flux<Integer> integerFlux()
    {
        return Flux.range(1,3)
                .doOnSubscribe(s-> System.out.println("Subscribed"))
                .doOnComplete(()-> System.out.println("**Completed"))
                .map(i-> i / (Util.faker().random().nextInt(1,5) > 3 ? 0 : 1 ))
                .doOnError(s-> System.out.println("Error : "+s));
    }
}
