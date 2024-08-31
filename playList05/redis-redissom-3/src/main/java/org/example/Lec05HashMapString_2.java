package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;

import java.util.Map;

public class Lec05HashMapString_2 {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RMapReactive<String,String> map = client.getMap("product:1", StringCodec.INSTANCE) ;

        Map<String ,String> mapJava = Map.of(
                "name","Samsung 22 ultra" ,
                "category","informatique" ,
                "price","1400"

        ) ;

        map.putAll(mapJava).subscribe() ;


        Thread.sleep(50000);


    }
}
