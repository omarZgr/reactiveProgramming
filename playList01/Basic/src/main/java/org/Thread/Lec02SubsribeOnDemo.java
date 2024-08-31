package org.Thread;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec02SubsribeOnDemo {

    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(objectFluxSink ->
        {
            printThreadName("create");
            objectFluxSink.next(1) ;
        })
                .doOnNext(i->printThreadName("next " +i)) ;

      flux
              .doFirst(() -> printThreadName("first 2 "))
              .subscribeOn(Schedulers.boundedElastic())
              .doFirst(() -> printThreadName("first 1 "))
              .subscribe(v->printThreadName("sub : " +v));


        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg)
    {
        System.out.println(msg + "\t\t:  Thread >> " + Thread.currentThread().getName() );
    }
}
