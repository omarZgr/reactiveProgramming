package com.zougrar.assigment_trending_product_redis.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Data
public class Product {

    @Id
    private Integer id ;
    private String name ;
    private Float price ;
}
