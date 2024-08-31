package org.example;

import org.example.config.RedissonConfig;
import org.example.entity.User;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Mono;

import java.util.Map;

public class Lec06HashMapObject {


    public static void main(String[] args) throws InterruptedException {

        // Map<Integer,User>

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        TypedJsonJacksonCodec codec = new TypedJsonJacksonCodec(Integer.class, User.class) ;

        RMapReactive<Integer,User> map = client.getMap("users", codec) ;

        User user1 = User.create(1,"omar zougrar","morocco") ;
        User user2 = User.create(2,"jama zougrar","canada") ;

        map.put(1,user1).subscribe() ;
        map.put(2,user2).subscribe() ;

        /// to see result on terminal , tap : hgetall users




        Thread.sleep(50000);


    }
}
