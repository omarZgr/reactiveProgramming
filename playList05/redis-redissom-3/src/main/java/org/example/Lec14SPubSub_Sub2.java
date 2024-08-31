package org.example;

import org.example.config.RedissonConfig;
import org.redisson.api.RTopicReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;

public class Lec14SPubSub_Sub2 {

    public static void main(String[] args) throws InterruptedException {

        RedissonReactiveClient client = RedissonConfig.configReactiveRedis();

        RTopicReactive topic = client.getTopic("my-room", StringCodec.INSTANCE) ;

        subsriber2(topic) ;
    }


    public static void subsriber2(RTopicReactive topic) throws InterruptedException {
        topic.getMessages(String.class)
                .doOnNext(i-> System.out.println("Sub 2 : " + i))
                .subscribe();

        Thread.sleep(10000);
    }

}
