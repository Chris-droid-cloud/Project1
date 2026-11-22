package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dao;


import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findByName(String name);
    public List<Book> findByAuthor(String author);
    public List<Book> findByGenre(String genre);
    public List<Book> findByYear(int year);


    List<Book> findByNameStartingWith(String first);
    List<Book> findByAuthorStartingWith(String first);
    List<Book> findByGenreStartingWith(String first);
    List<Book> findByYearStartingWith(int year);


}
