package com.zougrar.assigment_trending_product_redis.repository;

import com.zougrar.assigment_trending_product_redis.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer > {
}
