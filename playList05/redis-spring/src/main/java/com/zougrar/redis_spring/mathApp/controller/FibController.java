package com.zougrar.redis_spring.mathApp.controller;


import com.zougrar.redis_spring.mathApp.service.FibService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("math")
@RequiredArgsConstructor
public class FibController {

    private final FibService fibService ;


    @GetMapping(value = "/fib/{number}/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Integer> getFib(@PathVariable int number,@PathVariable String name) {
        return Mono.fromSupplier(() -> this.fibService.getFin(number,name));
    }

    @GetMapping(value = "/fib/{number}/clear", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Void> clearCache(@PathVariable int number) {
        return Mono.fromRunnable(() -> this.fibService.clearCache(number)) ;
    }


}
