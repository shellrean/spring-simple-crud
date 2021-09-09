package com.shellrean.app0003.controller;

import java.util.Optional;

import com.shellrean.app0003.dto.BookResponseData;
import com.shellrean.app0003.dto.BookStoreData;
import com.shellrean.app0003.entity.Book;
import com.shellrean.app0003.entity.Category;
import com.shellrean.app0003.entity.Publisher;
import com.shellrean.app0003.repository.BookRepository;
import com.shellrean.app0003.repository.CategoryRepository;
import com.shellrean.app0003.repository.PublisherRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BookResponseData> store(@RequestBody BookStoreData bookStoreData) {
        try {
            Optional<Category> category = categoryRepository.findById(bookStoreData.getCategoryId());
            if (!category.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Optional<Publisher> publisher = publisherRepository.findById(bookStoreData.getPublisherId());
            if (!category.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Book book = modelMapper.map(bookStoreData, Book.class);
            book.setCategory(category.get());
            book.setPublisher(publisher.get());
            
            book = bookRepository.save(book);

            BookResponseData bookResponseData = modelMapper.map(book, BookResponseData.class);

            return new ResponseEntity<>(bookResponseData, HttpStatus.OK);
        } catch (JpaSystemException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
