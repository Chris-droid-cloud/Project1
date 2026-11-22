package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.service;

import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dto.BookDto;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BookService {

    public List<Book> getAllBooks();

    public void saveBook(Book book);

    public Book getBook(int id);

    public void deleteBook(int id);

    public List<Book> findByName(String name);
    public List<Book> findByAuthor(String author);
    public List<Book> findByGenre(String genre);
    public List<Book> findByYear(int year);


    List<Book> findByNameStartingWith(String first);
    List<Book> findByAuthorStartingWith(String first);
    List<Book> findByGenreStartingWith(String genre);

    String create(BookDto bookDto);
}
