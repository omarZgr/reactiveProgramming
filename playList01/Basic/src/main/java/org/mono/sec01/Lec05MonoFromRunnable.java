package org.mono.sec01;

import org.mono.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec05MonoFromRunnable {

    public static void main(String[] args) {

        Mono.fromRunnable(process())
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        () -> System.out.println("process is done ,sending  emails ..")
                ) ;

    }

    private static Runnable process()
    {
        return () -> {
            System.out.println("Some operations doing here ..");
        }  ;
    }
}
