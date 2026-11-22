package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.service;


import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dao.BookRedisRepository;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity.BookRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookRedisService {

    @Autowired
    private final BookRedisRepository bookRedisRepository;

    public BookRedisService(BookRedisRepository userRepo) {
        this.bookRedisRepository = userRepo;
    }

    public BookRedis save(BookRedis user) {
        return bookRedisRepository.save(user);
    }

    @Cacheable(value = "books", key="#id")
    public BookRedis getById(int id) {
        return bookRedisRepository.findById(id).orElse(null);
    }
}
