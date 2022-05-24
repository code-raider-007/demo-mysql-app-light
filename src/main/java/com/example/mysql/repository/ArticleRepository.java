package com.example.mysql.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.mysql.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
