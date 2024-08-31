package org.fluxAdvanced.sec01.helper;

import org.flux.courseutil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink ;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink ;

    }

    public void produce()
    {
        String name = Util.faker().country().name()  ;
        String thread = Thread.currentThread().getName();
        this.fluxSink.next(thread+" : "+name) ;
    }
}
