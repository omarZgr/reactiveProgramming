package org.flux.sec01;

import org.flux.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Lec06FluxVsList {

    public static void main(String[] args) throws InterruptedException {

        List<String> dataList = getNamesList(5)  ;
        System.out.println(dataList);

        System.out.println("---------------");

        getNamesFlux(5).subscribe(Util.onNext()) ;

    }

    private static List<String> getNamesList(int count) throws InterruptedException {
        List<String> strings = new ArrayList<>() ;
        for (int i=0;i<count;i++)
        {
            strings.add(getName()) ;
        }

        return strings ;

    }

    private static Flux<String> getNamesFlux(int count)
    {
        return Flux.range(1,count)
                .map(i-> {
                    try {
                        return getName();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }) ;
    }
    private static String getName() throws InterruptedException {
        Util.sleepSeconds(1); ;
        return Util.faker().name().fullName() ;
    }
}
