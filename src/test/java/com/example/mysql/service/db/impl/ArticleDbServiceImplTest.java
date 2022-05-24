package com.example.mysql.service.db.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mysql.entity.Article;
import com.example.mysql.service.db.ArticleDbService;

@SpringBootTest
class ArticleDbServiceImplTest {
	
	
	
	@Autowired
	private ArticleDbService articleDbService;

	@BeforeEach
	void setUp() throws Exception {
		
		Article article = getNewArticle("Article Title", "This is the body of the article");
		this.articleDbService.saveOrUpdateArticle(article);
		Article article2 = getNewArticle("Second Article Title", "This is the body of the second article");
		this.articleDbService.saveOrUpdateArticle(article2);
	}

	@Test
	void testGetAllArticles() {
		List<Article> articles = this.articleDbService.getAllArticles();
		
		assertNotNull(articles);
		assertEquals(articles.size(), 2);
	}

	@Test
	void testGetArticle() {
		Article article = this.articleDbService.getArticle(Long.valueOf(1));
		assertNotNull(article);
		assertEquals(article.getTitle(), "Article Title");
	}

	@Test
	void testSaveOrUpdateArticle() {
		Article article = this.getNewArticle("Third Article Title", "This is the body of the third article");
		article = this.articleDbService.saveOrUpdateArticle(article);
		
		assertNotNull(article);
		assertNotNull(article.getArticleId());
	}

	@Test
	void testDeleteArticle() {
		Article article = this.articleDbService.getArticle(Long.valueOf(1));
		assertNotNull(article);
		assertEquals(article.getArticleId(), Long.valueOf(1));
		
		this.articleDbService.deleteArticle(article.getArticleId());
		
		Exception exception = assertThrows(NoSuchElementException.class, () ->{
			this.articleDbService.getArticle(Long.valueOf(1));
		});
		
		assertTrue(exception.getMessage().contains("No value present"));
		
	}
	
	private Article getNewArticle(String title, String body) {
		Article article = new Article();
		article.setTitle(title);
		article.setAuthor("Code Raider");
		article.setBody(body);
		return article;
	}

}
