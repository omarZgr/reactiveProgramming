package org.mono.sec01;

import org.mono.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscibe {

    public static void main(String[] args) {

        Mono<Integer> mono = Mono.just("Omar")
                .map(String::length)
                .map(l -> l / 0);

        mono.subscribe(
                Util.onNext(),
                Util.onError() ,
                Util.onComplet()
        ) ;
    }
}
