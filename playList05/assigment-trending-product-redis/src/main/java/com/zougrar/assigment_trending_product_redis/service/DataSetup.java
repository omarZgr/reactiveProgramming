package com.zougrar.assigment_trending_product_redis.service;


import com.github.javafaker.Faker;
import com.zougrar.assigment_trending_product_redis.entity.Product;
import com.zougrar.assigment_trending_product_redis.repository.ProductRepository;
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

@Service
@RequiredArgsConstructor
public class DataSetup implements CommandLineRunner {

    private final ProductRepository productRepository ;
    private final R2dbcEntityTemplate entityTemplate ;

    @Value("classpath:init.sql")
    private Resource resource ;

    @Override
    public void run(String... args) throws Exception {

        String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8) ;
        System.out.println(sql);

        Faker faker = Faker.instance();

        Mono<Void> insert = Flux.range(1, 100)
                .map(i -> new Product(null, faker.commerce().productName(), Float.valueOf(faker.commerce().price().replace(",","."))))
                .flatMap(this.productRepository::save)
                .then();


        this.entityTemplate.getDatabaseClient()
                .sql(sql)
                .then()
                .then(insert)
                .doFinally(s-> System.out.println("data setup done" + s))
                .subscribe() ;

    }
}
