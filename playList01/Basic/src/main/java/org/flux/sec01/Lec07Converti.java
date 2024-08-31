package org.flux.sec01;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07Converti {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("data")
                .doOnNext(next-> System.out.println("(From Mono)Emitting :"+next));

        mono.subscribe(item-> System.out.println("(From Mono) Received : "+item)) ;

        Flux<String> flux = Flux.from(mono)
                .doOnNext(next-> System.out.println("(From Flux)Emitting :"+next));


        flux.subscribe(item-> System.out.println("(From Flux) Received : "+item)) ;

        Flux<Integer > flux1 = Flux.range(1,80) ;
        Mono<Integer> mono1 = flux1.next() ;

        mono1.subscribe(item-> System.out.println("(From mono1) Received : "+item)) ;




    }
}
