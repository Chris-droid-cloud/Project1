package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity;


import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Book")
public class BookRedis implements Serializable {
    private int id;
    private String name;
    private String author;
    private String genre;
    private int year;
}
