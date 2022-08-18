package com.example.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.model.News;

public interface NewsRepository extends JpaRepository<News, Long>{

}
