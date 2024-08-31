package org.fluxAdvanced.sec01;

import org.flux.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {

        Flux.create(fluxSink ->
        {

            for (int i=0;i<10;i++)
                fluxSink.next(Util.faker().name().fullName()) ;
        })
                .subscribe(Util.onNext());
    }


}
