package com.shellrean.app0003.repository;

import com.shellrean.app0003.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
