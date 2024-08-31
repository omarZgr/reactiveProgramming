package com.zougrar.assigment_trending_product_redis.service.playlist;

import lombok.RequiredArgsConstructor;
import org.redisson.api.BatchOptions;
import org.redisson.api.RBatchReactive;
import org.redisson.api.RScoredSortedSetReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessMetricsService {

    private final RedissonReactiveClient client ;

    public Mono<Map<Integer,Double>> top3Products()
    {
        String format= DateTimeFormatter.ofPattern("YYYYMMdd").format(LocalDate.now())  ;
        RScoredSortedSetReactive<Integer> set = client.getScoredSortedSet("product:visit:"+format, IntegerCodec.INSTANCE) ;

       return set.entryRangeReversed(0,2)
                .map(listSe->listSe.stream().collect(
                        Collectors.toMap(
                                ScoredEntry::getValue,
                                ScoredEntry::getScore,
                                (a,b) ->a,
                                LinkedHashMap::new
                        )
                )) ;

    }
}
