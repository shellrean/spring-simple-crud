package com.shellrean.app0003.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.shellrean.app0003.dto.PublisherData;
import com.shellrean.app0003.entity.Publisher;
import com.shellrean.app0003.repository.PublisherRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/publishers")
public class PublisherController {
    
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PublisherData>> index() {
        try {
            List<Publisher> publishers = publisherRepository.findAll();
            List<PublisherData> result = publishers.stream()
                                                        .map(x -> modelMapper.map(x, PublisherData.class))
                                                        .collect(Collectors.toList());
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<PublisherData> store(@RequestBody PublisherData publisherData) {
        try {
            Publisher publisher = modelMapper.map(publisherData, Publisher.class);

            return new ResponseEntity<>(modelMapper.map(publisherRepository.save(publisher), PublisherData.class), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PublisherData> show(@PathVariable Long id) {
        try {
            Optional<Publisher> publisher = publisherRepository.findById(id);
            if(!publisher.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(modelMapper.map(publisher.get(), PublisherData.class));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<PublisherData> update(@RequestBody PublisherData publisherData) {
        try {
            Optional<Publisher> publisherCheck = publisherRepository.findById(publisherData.getId());
            if (!publisherCheck.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Publisher publisher = modelMapper.map(publisherData, Publisher.class);

            return ResponseEntity.ok(modelMapper.map(publisherRepository.save(publisher), PublisherData.class));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        try {
            Optional<Publisher> publisherCheck = publisherRepository.findById(id);
            if (!publisherCheck.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            publisherRepository.deleteById(id);
            
            return ResponseEntity.ok(String.format("Publisher with id %d deleted", id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}