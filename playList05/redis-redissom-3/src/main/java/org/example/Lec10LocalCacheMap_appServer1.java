package org.example;

import org.example.config.LocalCacheMap;
import org.example.entity.User;
import org.redisson.api.RLocalCachedMap;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec10LocalCacheMap_appServer1 {

    public static void main(String[] args) throws InterruptedException {


        RLocalCachedMap<Integer, User> usersMap  =  LocalCacheMap.setup();

        appServer1(usersMap) ;





    }

    private static void appServer1(RLocalCachedMap<Integer, User> usersMap) throws InterruptedException {

        User user1 = User.create(1,"omar zougrar","morocco") ;
        User user2 = User.create(2,"jama zougrar","canada") ;

        usersMap.put(1,user1) ;
        usersMap.put(2,user2) ;

        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println(i + " ==> "+usersMap.get(1)))
                .subscribe();


        Thread.sleep(600000);
    }


}
