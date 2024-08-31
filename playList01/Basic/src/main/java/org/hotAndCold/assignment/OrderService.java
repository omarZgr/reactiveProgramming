package org.hotAndCold.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderService {


    private Flux<PurchaseOrder> flux ;

    public  Flux<PurchaseOrder> orderStream()
    {
        if (Objects.isNull(flux))
            this.flux = getOrderStream() ;
        return flux ;
    }

    private Flux<PurchaseOrder> getOrderStream()
    {
        return Flux.interval(Duration.ofMillis(2000))
                .map(i->new PurchaseOrder())
                .publish()
                .refCount(2);
    }
}
