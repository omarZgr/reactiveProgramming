package org.flux.sec01;

import org.flux.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec02FluxFromIterable {

    public static void main(String[] args) {

        List<String > strings = Arrays.asList("Salam","omar","Bikhir") ;

        Flux<String> flux = Flux.fromIterable(strings) ;

        flux.subscribe(Util.onNext());
    }
}
