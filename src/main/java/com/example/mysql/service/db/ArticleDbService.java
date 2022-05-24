package com.example.mysql.service.db;

import java.util.List;

import com.example.mysql.entity.Article;

public interface ArticleDbService {

	List<Article> getAllArticles();
	Article getArticle(Long articleId);
	Article saveOrUpdateArticle(Article article);
	void deleteArticle(Long articleId);
}
