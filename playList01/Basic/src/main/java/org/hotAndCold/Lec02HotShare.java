package org.hotAndCold;

import org.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec02HotShare {
    public static void main(String[] args) throws InterruptedException {

        Flux<String> movieStream = Flux.fromStream(() ->getMovies())
                .delayElements(Duration.ofSeconds(2))
                .share();

        movieStream.subscribe(Util.subscriber("omar"));

        Util.sleepSeconds(5);

        movieStream.subscribe(Util.subscriber("jamal"));

        Util.sleepSeconds(50);


    }

    private static Stream<String > getMovies()
    {
        System.out.println("Go the movie streaming req");
        return Stream.of(
                "Scene 1" ,
                "Scene 2" ,
                "Scene 3" ,
                "Scene 4" ,
                "Scene 5" ,
                "Scene 6" ,
                "Scene 7" ,
                "Scene 8"
        )  ;
    }
}