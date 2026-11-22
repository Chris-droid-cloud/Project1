package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.service;

import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.KafkaEvent.EventCreated;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dao.BookRepository;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.dto.BookDto;
import com.zaurtregulov.spring.springboot.spring_course_springboot1_2.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
    public class BookServiceImpl implements BookService {

        @Autowired
        private BookRepository bookRepository;

        @Autowired
        KafkaTemplate<String, EventCreated> kafkaTemplate;

        @Override
        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        @Override
        @CachePut(value = "books", key = "#book.id")
        public void saveBook(Book book) {
            bookRepository.save(book);
        }

        @Override
        @Cacheable(value = "books", key = "#id")
        public Book getBook(int id) {
            Book book = null;
            Optional<Book> optional = bookRepository.findById(id);
            if (optional.isPresent()) {
                book = optional.get();
            }
            return book;
        }

        @Override
        @CacheEvict(value = "books", key = "#id")
        public void deleteBook(int id) {
            bookRepository.deleteById(id);
        }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
            return books;
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = bookRepository.findByName(name);
        return books;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        return books;
    }

    @Override
    public List<Book> findByYear(int year) {
        List<Book> books = bookRepository.findByYear(year);
        return books;
    }

    @Override
    public List<Book> findByNameStartingWith(String first) {
        List<Book> books = bookRepository.findByNameStartingWith(first);
        return books;
    }

    @Override
    public List<Book> findByAuthorStartingWith(String first) {
        List<Book> books = bookRepository.findByAuthorStartingWith(first);
        return books;
    }

    @Override
    public List<Book> findByGenreStartingWith(String first) {
        List<Book> books = bookRepository.findByGenreStartingWith(first);
        return books;
    }

    @Override
    public String create(BookDto bookDto) {
        String eventId = UUID.randomUUID().toString();
        EventCreated eventCreated = new EventCreated(
                eventId,
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getYear()
        );

//        SendResult<String, EventCreated> result = kafkaTemplate
//                .send("created-event-topic", eventId, eventCreated).get();

//        System.out.println("Topic " + result.getRecordMetadata().topic());
//        System.out.println("Partition " + result.getRecordMetadata().partition());
//        System.out.println("Offset " + result.getRecordMetadata().offset());
//        System.out.println("Timestamp " + result.getRecordMetadata().timestamp());

        //System.out.println("EventId: " + eventId);
        CompletableFuture<SendResult<String, EventCreated>> future = kafkaTemplate
                .send("created-event-topic", eventId, eventCreated);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                System.out.println("Failed to send message " + exception.getMessage());
            } else {
                System.out.println("Successfully sent message " + result.getRecordMetadata().toString());
            }
        });

        System.out.println("Created event id " + eventId);
        return eventId;
    }


}


