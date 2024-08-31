package org.operator.highLevel;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

public class RateLimitedProcessor {

    public static void main(String[] args) {
        // Generate a high rate of data (e.g., user activities)
        Flux<Integer> activityStream = generateUserActivities();

        // Limit the rate of data processing
        activityStream
                .limitRate(5)  // Limit to 5 items per second
                .publishOn(Schedulers.boundedElastic()) // Use a bounded elastic scheduler for processing
                .doOnNext(activity -> System.out.println("Processing activity: " + activity))
                .subscribe(
                        activity -> {
                            // Simulate processing of user activities
                            processActivity(activity);
                        },
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Stream completed")
                );

        // Keep the application running to see results
        try {
            Thread.sleep(10000); // Run for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Flux<Integer> generateUserActivities() {
        return Flux.create(emitter -> {
            Random random = new Random();
            // Simulate a high rate of user activities
            while (!emitter.isCancelled()) {
                int activity = random.nextInt(100); // Generate a random user activity
                emitter.next(activity);
                try {
                    Thread.sleep(100); // Simulate a high frequency of activity generation
                } catch (InterruptedException e) {
                    emitter.error(e);
                }
            }
        });
    }

    private static void processActivity(Integer activity) {
        // Simulate processing of user activities
        System.out.println("Activity processed: " + activity);
    }
}
