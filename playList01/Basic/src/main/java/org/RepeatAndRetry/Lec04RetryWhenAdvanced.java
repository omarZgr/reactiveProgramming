package org.RepeatAndRetry;

import org.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec04RetryWhenAdvanced {

    public static void main(String[] args) throws InterruptedException {

        orderService(Util.faker().business().creditCardNumber())
                .doOnError(err -> System.out.println("Error: " + err.getMessage()))
                .retryWhen(Retry.from(retrySignalFlux ->
                        retrySignalFlux
                                .doOnNext(rs -> {
                                    System.out.println("Total Retries: " + rs.totalRetries());
                                    System.out.println("Failure: " + rs.failure().getMessage());
                                })
                                .handle((retrySignal, synchronousSink) -> {
                                    if ("500".equals(retrySignal.failure().getMessage())) {
                                        synchronousSink.next(1); // continue retry
                                    } else {
                                        synchronousSink.error(retrySignal.failure()); // propagate error and stop retrying
                                    }
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static Mono<String> orderService(String ccNumber) {
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Util.faker().idNumber().valid();
        });
    }

    private static void processPayment(String ccNumber) {
        int random = Util.faker().random().nextInt(0, 10);

        if (random >= 5) {
            throw new RuntimeException("500");
        } else {
            throw new RuntimeException("404");
        }
    }
}
