package com.shellrean.app0003.dto;

public class BookStoreData {
    private Long id;
    private String name;
    private String author;
    private Integer pageTotal;
    private Integer publisYear;
    private Long categoryId;
    private Long publisherId;
    
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
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getPublisherId() {
        return publisherId;
    }
    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }
}
