package com.zougrar.redis_redisson_demo.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String id;
    private String username;
    private String email;
    private String sex;

}
