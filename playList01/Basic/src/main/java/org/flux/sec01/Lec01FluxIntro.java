package org.flux.sec01;

import org.flux.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1,2,3,5)  ;

        flux.subscribe(Util.onNext());
    }
}
