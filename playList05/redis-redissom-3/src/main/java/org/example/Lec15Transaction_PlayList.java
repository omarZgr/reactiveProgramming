package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RTransactionReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.LongCodec;

public class Lec15Transaction_PlayList {

    public static void main(String[] args) {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RBucketReactive<Long> user1 = setupAmountFor1(client) ;
        RBucketReactive<Long> user2 = setupAmountFor2(client) ;


        RTransactionReactive transaction= client.createTransaction(TransactionOptions.defaults()) ;

        RBucketReactive<Long> user1balance = transaction.getBucket("user:1:balance", LongCodec.INSTANCE) ;
        RBucketReactive<Long> user2balance = transaction.getBucket("user:2:balance", LongCodec.INSTANCE) ;

       transaction(transaction,user1balance,user2balance,50L) ;


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



    public static void transaction(RTransactionReactive transaction,RBucketReactive<Long> user1,RBucketReactive<Long> user2,Long amount)
    {


        user1.get()
                .doOnNext(user1Balance-> System.out.println("We check { user1Balance->user1Balance.longValue() > amount } ==> "+ (user1Balance.longValue() > amount)))
                .filter(user1Balance->user1Balance.longValue() > amount)
                .doOnNext(s-> System.out.println("Yes we can do now transaction "))
                .map(user1Balance->user1.set(user1Balance - amount).subscribe())
                .map(i-> user2.set(amount).subscribe())
                .then(transaction.commit())
                .doOnError(ex->transaction.rollback())
                .subscribe() ;

    }
}
