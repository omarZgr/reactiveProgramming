package org.Thread;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec01ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(objectFluxSink ->
        {
            printThreadName("create");
            objectFluxSink.next(1) ;
        })
                .doOnNext(i->printThreadName("next " +i)) ;

       Runnable runnable = ()->flux.subscribe(v->printThreadName("sub : " +v));

       for (int i=0;i<2;i++)
       {
           new Thread(runnable).start();
       }

        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg)
    {
        System.out.println(msg + "\t\t:  Thread >> " + Thread.currentThread().getName() );
    }
}
