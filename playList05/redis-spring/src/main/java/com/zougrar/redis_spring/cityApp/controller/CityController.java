package com.zougrar.redis_spring.cityApp.controller;


import com.zougrar.redis_spring.cityApp.dto.City;
import com.zougrar.redis_spring.cityApp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService  cityService ;

    @GetMapping(value = "/{zipcode}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<City> getWeather(@PathVariable String zipcode)
    {
        return this.cityService.getCity(zipcode) ;
    }


}
