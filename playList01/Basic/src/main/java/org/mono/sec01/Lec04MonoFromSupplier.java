package org.mono.sec01;

import org.mono.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoFromSupplier {

    public static void main(String[] args) {

      //  Mono<String> mono = Mono.just(getName()) ;
        Mono<String> mono = Mono.fromSupplier(()->getName()) ;

    }

    private static String getName()
    {
        System.out.println("Generating name...");
        return Util.faker().name().fullName() ;
    }

}
