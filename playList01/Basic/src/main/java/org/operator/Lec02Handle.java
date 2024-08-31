package org.operator;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec02Handle {


    public static void main(String[] args) {

        Flux.range(1,10).handle((current, synSink)->
        {
            if (current==7) synSink.complete();
            else {
                System.out.println("Emiting : "+current);
                synSink.next(current);
            }
        }).subscribe(Util.subscriber()) ;
    }
}
