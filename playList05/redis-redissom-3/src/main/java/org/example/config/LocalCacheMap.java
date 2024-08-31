package org.example.config;

import org.example.entity.User;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;

public class LocalCacheMap {

    public static RLocalCachedMap<Integer, User> setup()
    {
        RedissonClient redissonClient = RedissonConfig.configRedis() ;

        LocalCachedMapOptions<Integer, User> mapOptions = LocalCachedMapOptions.<Integer,User>defaults()
                .syncStrategy(LocalCachedMapOptions.SyncStrategy.UPDATE)
                .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.NONE)  ;

        return  redissonClient.getLocalCachedMap(
                "usersCache",
                new TypedJsonJacksonCodec(Integer.class, User.class) ,
                mapOptions
        ) ;
    }
}
