package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.KafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventCreated {

    private String eventId;
    private BigDecimal id;
    private String name;
    private String author;
    private String genre;
    private BigDecimal year;

}
