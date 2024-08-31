package org.flux.sec01;

import org.flux.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Lec04FluxFromRange {

    public static void main(String[] args) {

        //Subscriber

        Flux.range(1,4)
                .log()
                .map(i-> Util.faker().name().fullName())
                .log()
                .subscribe(Util.onNext());

    }
}
