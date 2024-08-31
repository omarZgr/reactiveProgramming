package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;

public class Lec05HashMapString_1 {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        RMapReactive<String,String> map = client.getMap("product:1", StringCodec.INSTANCE) ;

         map.put("name","phone 14" ).subscribe();
         map.put("category","informatique" ).subscribe();
         map.put("price","1500" ).subscribe();

        Thread.sleep(50000);


    }
}
