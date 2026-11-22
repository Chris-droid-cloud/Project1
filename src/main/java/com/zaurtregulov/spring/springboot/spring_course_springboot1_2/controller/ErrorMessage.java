package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Date timestamp;
    private String message;

}
