package org.flux.sec01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicReference;

public class Lec05FluxCustomerSubsriber {

    public static void main(String[] args) throws InterruptedException {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>() ;

        Flux.range(1,7)
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe method ...");
                        atomicReference.set(s);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("OnNext >> "+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError >> "+t);


                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete >> ");


                    }
                });

       atomicReference.get().request(3);
       Thread.sleep(3000);
        atomicReference.get().request(3);
        Thread.sleep(3000);
        atomicReference.get().cancel();


    }



}
