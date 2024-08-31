package com.zougrar.redis_spring.mathApp.service;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FibService {



    @Cacheable(value = "math:fib",key = "#number")
    public int getFin(int number,String name)
    {
        System.out.println("Calculating fib for : " +number + " for user :: " + name);
        return this.fib(number) ;
    }

    @CacheEvict(value = "math:fib",key = "#number")
    public void clearCache(int number)
    {
        System.out.println("clear caching");
    }










    private int fib(int number)
    {
        if (number <2)
            return number ;
        return fib(number - 1) + fib(number-2) ;
    }
}
