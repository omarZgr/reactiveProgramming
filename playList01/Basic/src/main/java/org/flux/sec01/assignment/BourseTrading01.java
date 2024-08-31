package org.flux.sec01.assignment;

import org.flux.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class BourseTrading01 {

    public static void main(String[] args) throws InterruptedException {


        Flux.interval(Duration.ofSeconds(1))
                .map(price -> cuurentPrice())
                .filter(i ->i >8  || i< 2)
                .subscribe(Util.onNext());






        Thread.sleep(70000);


    }



    private static Float cuurentPrice()
    {
        Random random = new Random() ;



        return random.nextFloat(0,10);
    }
}
