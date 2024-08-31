package org.flux.courseutil;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Util {

    private final static Faker FAKER = Faker.instance() ;
    public static Consumer<Object> onNext()
    {
        return o -> System.out.println("Received : "+o)  ;
    }

    public static Consumer<Throwable> onError()
    {
        return e -> System.out.println("Error : "+e)  ;
    }

    public static Runnable onComplet()
    {
        return () -> System.out.println("Completed")  ;
    }

    public static Faker faker()
    {
        return FAKER ;
    }

    public static void sleepSeconds(int seconde) throws InterruptedException {
        Thread.sleep(seconde * 1000);
    }
}
