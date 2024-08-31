package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;

public class Lec15Transaction_MyTry {

    public static void main(String[] args) {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RBucketReactive<Long> user1 = setupAmountFor1(client) ;
        RBucketReactive<Long> user2 = setupAmountFor2(client) ;

        transaction(user1,user2,50L) ;


    }

    public static RBucketReactive<Long> setupAmountFor1(RedissonReactiveClient client)
    {
        RBucketReactive<Long> user1balance = client.getBucket("user:1:balance", LongCodec.INSTANCE) ;

        user1balance.set(100L).doOnNext(i-> System.out.println("We init user 1 by : "+i)).subscribe() ;

        return user1balance ;

    }


    public static RBucketReactive<Long> setupAmountFor2(RedissonReactiveClient client)
    {
        RBucketReactive<Long> user2balance = client.getBucket("user:2:balance", LongCodec.INSTANCE) ;

        user2balance.set(0L).doOnNext(i-> System.out.println("We init user 2 by : "+i)).subscribe() ;

        return user2balance ;

    }



    public static void transaction(RBucketReactive<Long> user1,RBucketReactive<Long> user2,Long amount)
    {


        user1.get()
                .doOnNext(user1Balance-> System.out.println("We check { user1Balance->user1Balance.longValue() > amount } ==> "+ (user1Balance.longValue() > amount)))
                .filter(user1Balance->user1Balance.longValue() > amount)
                .doOnNext(s-> System.out.println("Yes we can do now transaction "))
                .map(user1Balance->user1.set(user1Balance - amount).subscribe())
                .map(i-> user2.set(amount).subscribe())
                .subscribe() ;

    }
}
