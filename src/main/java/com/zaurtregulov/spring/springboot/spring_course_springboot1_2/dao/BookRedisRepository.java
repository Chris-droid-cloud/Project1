package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dao;

import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity.BookRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRedisRepository extends CrudRepository<BookRedis, Integer> {
}
