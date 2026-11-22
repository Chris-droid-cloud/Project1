package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private BigDecimal id;
    private String name;
    private String author;
    private String genre;
    private BigDecimal year;

}
