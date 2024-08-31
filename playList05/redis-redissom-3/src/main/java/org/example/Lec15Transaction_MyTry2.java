package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RTransactionReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec15Transaction_MyTry2 {

    public static void main(String[] args) {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

    RBucketReactive<Long> user1Balance = client.getBucket("user:1:balance", LongCodec.INSTANCE) ;
        RBucketReactive<Long> user2Balance = client.getBucket("user:2:balance", LongCodec.INSTANCE) ;



        user1Balance.set(100L)
                .doOnNext(i-> System.out.println("insert 100 to user 1"))
                .then(user2Balance.set(0L).doOnNext(i-> System.out.println("insert 0 to user 2")))
                .subscribe()
        ;







        RTransactionReactive transaction= client.createTransaction(TransactionOptions.defaults()) ;


       user1Balance = transaction.getBucket("user:1:balance", LongCodec.INSTANCE) ;
        user2Balance = transaction.getBucket("user:2:balance", LongCodec.INSTANCE) ;


        transfer(user1Balance,user2Balance,40L)
                .thenReturn(0)
           //     .map(i->(5/0))
                .then(transaction.commit())
                .doOnError(System.out::println)
                .doOnError(ex->transaction.rollback())
                .subscribe() ;
    }

    private static RBucketReactive<Long> initBalance(RTransactionReactive transaction, String key, long balance) {

        RBucketReactive<Long> userBalance = transaction.getBucket(key, LongCodec.INSTANCE) ;

        userBalance.set(balance).subscribe(i-> System.out.println("insert for "+key + " , value : "+ balance)) ;

        return userBalance ;
    }



    private static Mono<Void> transfer( RBucketReactive<Long> user1balance,RBucketReactive<Long> user2balance,long amount)
    {
        return Flux.zip(user1balance.get(),user2balance.get())
                .filter(t->t.getT1() >= amount)
                .flatMap(t->user1balance.set(t.getT1() - amount).thenReturn(t))
                .flatMap(t->user2balance.set(t.getT2() + amount))
                .then() ;

    }
}
