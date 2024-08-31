package org.fluxAdvanced.sec01.highLevel.create;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockPriceStreamer {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Flux<Float> getPriceStream(String stockSymbol) {
        return Flux.create(emitter -> {
            executor.submit(() -> {
                try {
                    while (!emitter.isCancelled()) {
                        // Simulate receiving price from an external system
                        float price = fetchPriceFromApi(stockSymbol);
                        emitter.next(price);
                        Thread.sleep(1000); // Simulate delay between updates
                    }
                } catch (Exception e) {
                    emitter.error(e);
                } finally {
                    emitter.complete();
                }
            });
        }, FluxSink.OverflowStrategy.BUFFER); // Buffer strategy for handling backpressure
    }

    private float fetchPriceFromApi(String stockSymbol) {
        // Simulate API call
        return (float) (Math.random() * 1000);
    }

    public static void main(String[] args) {
        StockPriceStreamer streamer = new StockPriceStreamer();
        streamer.getPriceStream("AAPL")
                .subscribe(price -> System.out.println("Received price: " + price),
                        err -> System.err.println("Error: " + err),
                        () -> System.out.println("Stream completed"));
    }
}
