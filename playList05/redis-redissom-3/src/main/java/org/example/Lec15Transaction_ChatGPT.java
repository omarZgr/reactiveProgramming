package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Mono;

public class Lec15Transaction_ChatGPT {

    public static void main(String[] args) {
        RedissonReactiveClient client = RedissonConfig.configReactiveRedis();

        RBucketReactive<Long> user1Balance = initializeUserBalance(client, "user:1:balance", 100L);
        RBucketReactive<Long> user2Balance = initializeUserBalance(client, "user:2:balance", 0L);

        executeTransaction(user1Balance, user2Balance, 50L)
                .doOnSuccess(success -> System.out.println("Transaction completed successfully"))
                .doOnError(error -> System.err.println("Transaction failed: " + error.getMessage()))
                .subscribe();
    }

    private static RBucketReactive<Long> initializeUserBalance(RedissonReactiveClient client, String key, Long initialBalance) {
        RBucketReactive<Long> balanceBucket = client.getBucket(key, LongCodec.INSTANCE);

        balanceBucket.set(initialBalance)
                .doOnNext(balance -> System.out.println("Initialized balance for " + key + " with amount: " + balance))
                .subscribe();

        return balanceBucket;
    }

    private static Mono<Void> executeTransaction(RBucketReactive<Long> user1Balance, RBucketReactive<Long> user2Balance, Long amount) {
        return user1Balance.get()
                .filter(balance -> balance >= amount)
                .flatMap(balance -> user1Balance.set(balance - amount)
                        .then(user2Balance.get()
                                .flatMap(user2Bal -> user2Balance.set(user2Bal + amount))))
                .doOnSuccess(ignored -> System.out.println("Transaction of " + amount + " completed from user1 to user2"))
                .switchIfEmpty(Mono.error(new IllegalStateException("Insufficient balance in user1's account")))
                .then();
    }
}
