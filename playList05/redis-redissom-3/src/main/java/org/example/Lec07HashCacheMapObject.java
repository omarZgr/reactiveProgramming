package org.example;

import org.example.config.RedissonConfig;
import org.example.entity.User;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.concurrent.TimeUnit;

public class Lec07HashCacheMapObject {

    public static void main(String[] args) throws InterruptedException {


        // Map<Integer,User>

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis() ;

        TypedJsonJacksonCodec codec = new TypedJsonJacksonCodec(Integer.class, User.class) ;

        RMapCacheReactive<Integer,User> mapCache = client.getMapCache("usersCache",codec) ;

        User user1 = User.create(1,"omar zougrar","morocco") ;
        User user2 = User.create(2,"jama zougrar","canada") ;

        mapCache.put(1,user1,10, TimeUnit.SECONDS).subscribe() ;
        mapCache.put(2,user2).subscribe() ;

        /// to see result on terminal , tap : hgetall users




        Thread.sleep(50000);

    }
}
