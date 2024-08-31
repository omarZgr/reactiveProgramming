package org.sink;

import org.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {


    public static void main(String[] args) {


        //MONO >>> 1 value / empty / error
       Sinks.One<Object>  sink = Sinks.one() ;

        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber("sam"))  ;

        sink.emitValue("Hi",(signalType, emitResult) ->
        {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());

            return false ;
        });


        sink.emitValue("Hi",(signalType, emitResult) ->
        {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());

            return false ;
        });

    }
}
