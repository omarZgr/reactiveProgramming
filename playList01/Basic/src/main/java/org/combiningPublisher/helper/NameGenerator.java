package org.combiningPublisher.helper;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class NameGenerator {

    private ArrayList<String> list = new ArrayList<>() ;
    public Flux<String > generateNames()
    {
        return Flux.generate(stringSynchronousSink ->
        {
            try {
                Util.sleepSeconds(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String name = Util.faker().name().fullName() ;
            list.add(name)  ;
            stringSynchronousSink.next(name)
            ;
        })
                .cast(String.class)
                .startWith(getFromCache())
                ;
    }

    private Flux<String > getFromCache()
    {
        return Flux.fromIterable(list) ;
    }
}
