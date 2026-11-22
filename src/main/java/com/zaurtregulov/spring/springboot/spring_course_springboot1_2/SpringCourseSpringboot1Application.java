package com.zaurtregulov.spring.springboot.spring_course_springboot1_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCourseSpringboot1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCourseSpringboot1Application.class, args);
    }

}
