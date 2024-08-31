package org.flux.sec01.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class BourseTrading02 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1) ;



        // Start the price generation and subscription
        generatePrice().subscribeWith(new Subscriber<Float>() {
            private final AtomicReference<Subscription> atomicReference = new AtomicReference<>();

            @Override
            public void onSubscribe(Subscription s) {
                atomicReference.set(s);
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Float price) {
                System.out.println("onNext >> " + price);
                if (price > 180f || price < 20f) {
                    {
                        System.out.println("Price out of bounds, canceling subscription...");
                        atomicReference.get().cancel();
                        latch.countDown();
                    }

                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError >> " + t);
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete >> ");
                latch.countDown();

            }
        });

        // Keep the application alive to allow the Flux to emit values
        latch.await();
    }

    private static Flux<Float> generatePrice() {
        Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> random.nextFloat() * 200f);
    }
}
