package org.fluxAdvanced.sec01;

import org.flux.courseutil.Util;
import org.fluxAdvanced.sec01.helper.NameProducer;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactoring {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer()  ;

        Flux.create(nameProducer)
                .subscribe(Util.onNext());

        nameProducer.produce();

        Runnable runnable = nameProducer::produce;

        for (int i=0;i<10;i++)
            new Thread(runnable).start();
    }
}
