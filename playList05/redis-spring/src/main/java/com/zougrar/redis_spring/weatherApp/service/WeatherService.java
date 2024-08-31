package com.zougrar.redis_spring.weatherApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class WeatherService {

    @Autowired
    private ExternalServiceClient client ;

    @Cacheable("weather")
    public int getInfo(int zip)
    {
        System.out.println("getInfo() ");
        return 0 ;
    }

    @Scheduled(fixedRate = 10_000)
    public void update()
    {
        System.out.println("updating weather");
        IntStream.range(1,5).forEach(this.client::getWeatherInfo);
    }
}
