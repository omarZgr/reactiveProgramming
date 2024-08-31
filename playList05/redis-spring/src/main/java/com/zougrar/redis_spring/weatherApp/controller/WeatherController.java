package com.zougrar.redis_spring.weatherApp.controller;


import com.zougrar.redis_spring.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    public WeatherService weatherService ;

    @GetMapping(value = "/{zip}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Integer> getWeather(@PathVariable int zip)
    {
        return Mono.fromSupplier(()->this.weatherService.getInfo(zip)) ;
    }
}
