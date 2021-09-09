package com.shellrean.app0003.dto;

import com.shellrean.app0003.entity.Category;
import com.shellrean.app0003.entity.Publisher;

public class BookResponseData {
   
    private Long id;
    private String name;
    private String author;
    private Integer pageTotal;
    private Integer publisYear;
    private Category category;
    private Publisher publisher;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Integer getPageTotal() {
        return pageTotal;
    }
    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
    public Integer getPublisYear() {
        return publisYear;
    }
    public void setPublisYear(Integer publisYear) {
        this.publisYear = publisYear;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    
}
