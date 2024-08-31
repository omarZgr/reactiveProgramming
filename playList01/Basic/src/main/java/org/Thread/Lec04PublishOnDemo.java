package org.Thread;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04PublishOnDemo {


    public static void main(String[] args) throws InterruptedException {

        Flux<Object > flux = Flux.create(fluxSink ->
                {
                    currentThread("create");

                    for (int i = 0; i <4 ; i++) {
                        fluxSink.next(i) ;

                    }
                })
                .doOnNext(n->currentThread("next >> "+n)) ;


        flux
                .doOnNext(i-> currentThread("first2 >>" + i))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i-> currentThread("first1 >>" + i))
                .subscribe(v->currentThread("sub >> " +v)) ;

        Util.sleepSeconds(6);
    }

    private static void currentThread(String  msg)
    {
        System.out.println("Thread : " + Thread.currentThread().getName() +"\t->\tMessage : " + msg);
    }
}
