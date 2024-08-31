package org.fluxAdvanced.sec01.highLevel.push;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UserActionLogger {

    private final Queue<String> actionsQueue = new ConcurrentLinkedQueue<>();

    public Flux<String> getActionStream() {
        return Flux.push(emitter -> {
            while (!emitter.isCancelled()) {
                String action = actionsQueue.poll();
                if (action != null) {
                    emitter.next(action);
                }
            }
            emitter.complete();
        });
    }

    public void logAction(String action) {
        actionsQueue.offer(action);
    }

    public static void main(String[] args) throws InterruptedException {
        UserActionLogger logger = new UserActionLogger();

        // Simulate user actions
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                logger.logAction("User clicked button " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();

        logger.getActionStream()
                .subscribe(action -> System.out.println("Logged action: " + action),
                        err -> System.err.println("Error: " + err),
                        () -> System.out.println("Action stream completed"));
    }
}

