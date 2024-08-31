package org.batching;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03GroupBy {

    public static void main(String[] args) throws InterruptedException {

        Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i->i%2)
                .subscribe(gf->process(gf,gf.key()));

        Util.sleepSeconds(60);
    }

    public static void process(Flux<Integer> flux,int key)
    {
        flux.subscribe(i-> System.out.println("Key : "+ key + " , Item : "+ i));
    }
}
