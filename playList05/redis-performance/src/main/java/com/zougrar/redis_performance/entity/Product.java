package com.zougrar.redis_performance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@Table
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    private Integer id ;

    private String description ;
    private int price ;
}
