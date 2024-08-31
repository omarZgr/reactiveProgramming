package org.flux.sec01;

import org.flux.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromArrays {
    public static void main(String[] args) {

        Integer[]  integer = {4,2,6} ;

        Flux<Integer> flux = Flux.fromArray(integer) ;

        flux.subscribe(Util.onNext());
    }
}