package com.zougrar.redis_performance.service;


import com.zougrar.redis_performance.entity.Product;
import com.zougrar.redis_performance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {

    private final ProductRepository productRepository ;
    private final R2dbcEntityTemplate entityTemplate ;

    @Value("classpath:init.sql")
    private Resource resource ;

    @Override
    public void run(String... args) throws Exception {

        String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8) ;
        System.out.println(sql);

        Mono<Void> insert = Flux.range(1, 1000)
                .map(i -> new Product(null, "product- " + i, ThreadLocalRandom.current().nextInt(1, 100)))
                .collectList()
                .flatMapMany(l -> this.productRepository.saveAll(l))
                .then();

        this.entityTemplate.getDatabaseClient()
                .sql(sql)
                .then()
                .then(insert)
                .doFinally(s-> System.out.println("data setup done" + s))
                .subscribe() ;

    }
}
