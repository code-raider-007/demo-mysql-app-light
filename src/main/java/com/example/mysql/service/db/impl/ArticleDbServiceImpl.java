package com.example.mysql.service.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mysql.entity.Article;
import com.example.mysql.repository.ArticleRepository;
import com.example.mysql.service.db.ArticleDbService;

@Service
public class ArticleDbServiceImpl implements ArticleDbService {
	
	private final ArticleRepository articleRepository;

	public ArticleDbServiceImpl(ArticleRepository articleRepository) {
		super();
		this.articleRepository = articleRepository;
	}

	@Override
	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();
		this.articleRepository.findAll().forEach(article -> {
			articles.add(article);
		});
		return articles;
	}

	@Override
	public Article getArticle(Long articleId) {
		return this.articleRepository.findById(articleId).orElseThrow();
	}

	@Override
	public Article saveOrUpdateArticle(Article article) {
		return this.articleRepository.save(article);
	}

	@Override
	public void deleteArticle(Long articleId) {
		
		Article article = this.articleRepository.findById(articleId).orElseThrow();
		this.articleRepository.delete(article);
	}

}
