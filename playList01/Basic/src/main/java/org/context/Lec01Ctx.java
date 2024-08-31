package org.context;

import org.courseUtil.Util;
<<<<<<< HEAD
=======
import org.reactivestreams.Subscriber;
>>>>>>> b67f39c (First commit)
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Ctx {

    public static void main(String[] args) {

<<<<<<< HEAD
=======


>>>>>>> b67f39c (First commit)
        fnct()
                .contextWrite(Context.of("user","omar"))
                .subscribe(Util.subscriber()) ;
    }

    private static Mono<String > fnct()
    {
        return Mono.deferContextual(ctx->
        {
            if (ctx.hasKey("user"))
            {
                return Mono.just("Welcome " +ctx.get("user")) ;
            }
            else
                return Mono.error(new RuntimeException("unauthenticated")) ;
        }) ;
    }
}
