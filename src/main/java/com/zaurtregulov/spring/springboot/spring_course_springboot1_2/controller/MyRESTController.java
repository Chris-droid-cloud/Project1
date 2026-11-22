package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.controller;


import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dto.BookDto;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity.Book;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.service.BookService;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private BookService bookService;
    private S3Service s3Service;

    public MyRESTController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/books")
    public List<Book> showAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return allBooks;
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id) {
        Book book = bookService.getBook(id);
        return book;
    }

    @PostMapping("/books")
    public Book addNewBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book with ID = " + id + " was deleted";
    }

    @GetMapping("/books/name/{name}")
    public  List<Book> getBooksByName(@PathVariable String name) {
        List <Book> books = bookService.findByName(name);
        return books;
    }

    @GetMapping("/books/author/{author}")
    public  List<Book> getBooksByAuthor(@PathVariable String author) {
        List <Book> books = bookService.findByAuthor(author);
        return books;
    }

    @GetMapping("/books/genre/{genre}")
    public  List<Book> getBooksByGenre(@PathVariable String genre) {
        List <Book> books = bookService.findByGenre(genre);
        return books;
    }

    @GetMapping("books/year/{year}")
    public  List<Book> getBooksByYear(@PathVariable int year) {
        List <Book> books = bookService.findByYear(year);
        return books;
    }

    @GetMapping("/books/name/start/{first}")
    public  List<Book> getBooksByNameStartingWith(@PathVariable String first) {
        List <Book> books = bookService.findByNameStartingWith(first);
        return books;
    }

    @GetMapping("/books/author/start/{first}")
    public  List<Book> getBooksByAuthorStartingWith(@PathVariable String first) {
        List <Book> books = bookService.findByAuthorStartingWith(first);
        return books;
    }

    @GetMapping("/books/genre/start/{first}")
    public  List<Book> getBooksByGenreStartingWith(@PathVariable String first) {
        List <Book> books = bookService.findByGenreStartingWith(first);
        return books;
    }

    @GetMapping("file/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = s3Service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(s3Service.uploadFile(file), HttpStatus.OK);
    }

    @DeleteMapping("/file/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(s3Service.deleteFile(fileName), HttpStatus.OK);
    }

    @PostMapping("/kafka")
    public ResponseEntity<String> create(@RequestBody BookDto bookDto) {

        String eventId = bookService.create(bookDto);

        //try {
           // eventId = bookService.create(bookDto);
       // } catch (Exception e) {
         //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage()));
        //}
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId);
    }




}
