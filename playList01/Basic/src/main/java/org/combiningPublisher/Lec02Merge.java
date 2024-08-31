package org.combiningPublisher;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec02Merge {

    public static void main(String[] args) {

        Flux<String > flux = Flux.merge(
                Qatar.getFlights(),
                Emirate.getFlights(),
                Turkish.getFlights()
        ) ;

        flux.subscribe(Util.subscriber());

    }

    private static class Qatar
    {
        public static Flux<String > getFlights()
        {
            return Flux.range(1, Util.faker().random().nextInt(1,10))
                    .map(i->"Qatar " + Util.faker().random().nextInt(171,500))
                    .filter(i->Util.faker().random().nextBoolean()) ;
        }

    }

    private static class Emirate
    {
        public static Flux<String > getFlights()
        {
            return Flux.range(1, Util.faker().random().nextInt(1,6))
                    .map(i->"Emirate " + Util.faker().random().nextInt(171,500))
                    .filter(i->Util.faker().random().nextBoolean()) ;
        }

    }

    private static class Turkish
    {
        public static Flux<String > getFlights()
        {
            return Flux.range(1, Util.faker().random().nextInt(1,10))
                    .map(i->"Turkish " + Util.faker().random().nextInt(171,500))
                    .filter(i->Util.faker().random().nextBoolean()) ;
        }

    }
}
