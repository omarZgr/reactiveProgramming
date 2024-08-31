package org.fluxAdvanced.sec01.highLevel.generate;

import reactor.core.publisher.Flux;

public class OrderProcessing {

    private enum OrderState {
        RECEIVED, PROCESSING, SHIPPED, DELIVERED
    }

    public Flux<OrderState> processOrder() {
        return Flux.generate(() -> OrderState.RECEIVED, (state, sink) -> {
            switch (state) {
                case RECEIVED:
                    sink.next(OrderState.PROCESSING);
                    return OrderState.PROCESSING;
                case PROCESSING:
                    sink.next(OrderState.SHIPPED);
                    return OrderState.SHIPPED;
                case SHIPPED:
                    sink.next(OrderState.DELIVERED);
                    sink.complete();
                    return OrderState.DELIVERED;
                default:
                    sink.complete();
                    return state;
            }
        });
    }

    public static void main(String[] args) {
        OrderProcessing orderProcessing = new OrderProcessing();
        orderProcessing.processOrder()
                .subscribe(state -> System.out.println("Order state: " + state),
                        err -> System.err.println("Error: " + err),
                        () -> System.out.println("Order processing completed"));
    }
}
