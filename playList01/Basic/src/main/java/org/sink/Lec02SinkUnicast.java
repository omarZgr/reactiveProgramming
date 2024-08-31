package org.sink;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer() ;

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("omar"));

        //No work , cuz unicast is 1:1
        //flux.subscribe(Util.subscriber("jamal"));

        sink.tryEmitNext("Hi") ;
        sink.tryEmitNext("Bikhir") ;
        sink.tryEmitNext("CV") ;

    }
}
