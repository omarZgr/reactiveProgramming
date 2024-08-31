package com.zougrar.assigment_trending_product_redis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrendingHashMap {

    private Integer id ;
    private Long visit ;
}
