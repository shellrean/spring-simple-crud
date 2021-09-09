package com.shellrean.app0003.controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.shellrean.app0003.dto.CategoryRequestData;
import com.shellrean.app0003.dto.CategoryResponseData;
import com.shellrean.app0003.entity.Category;
import com.shellrean.app0003.repository.CategoryRepository;

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
@RequestMapping("api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponseData>> index() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseData> result  = categories.stream()
                                                        .map(x -> modelMapper.map(x, CategoryResponseData.class))
                                                        .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseData> store(@RequestBody CategoryRequestData categoryRequestData) {
        try {
            Category category = modelMapper.map(categoryRequestData, Category.class);
            CategoryResponseData result = modelMapper.map(categoryRepository.save(category), CategoryResponseData.class);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponseData> show(@PathVariable Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (!category.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            CategoryResponseData result = modelMapper.map(category.get(), CategoryResponseData.class);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus. INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<CategoryResponseData> update(@RequestBody CategoryRequestData categoryRequestData) {
        try {
            Optional<Category> categoryCheck = categoryRepository.findById(categoryRequestData.getId());
            if (!categoryCheck.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Category category = modelMapper.map(categoryRequestData, Category.class);
            CategoryResponseData result = modelMapper.map(categoryRepository.save(category), CategoryResponseData.class);

            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        try {
            Optional<Category> categoryCheck = categoryRepository.findById(id);
            if (!categoryCheck.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            categoryRepository.deleteById(id);

            return ResponseEntity.ok(String.format("category id %d deleted", id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
