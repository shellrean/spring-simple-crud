package com.shellrean.app0003.repository;

import com.shellrean.app0003.entity.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    
}
