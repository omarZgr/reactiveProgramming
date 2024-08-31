package org.mono.sec01;

import java.util.stream.Stream;

public class Lec01Stream {

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1)
                .map(i->{
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i* 2  ;
                })  ;

        //System.out.println(stream);
        stream.forEach(System.out::println);
    }
}
