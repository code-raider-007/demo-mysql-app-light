package com.example.mysql.service.db.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mysql.entity.Article;
import com.example.mysql.service.db.ArticleDbService;

@SpringBootTest
class ArticleDbServiceImplTest {
	
	private Long articleId;
	
	@Autowired
	private ArticleDbService articleDbService;

	@BeforeEach
	void setUp() throws Exception {
		
		Article article = getNewArticle("Article Title", "This is the body of the article");
		article = this.articleDbService.saveOrUpdateArticle(article);
		articleId = article.getArticleId();
		Article article2 = getNewArticle("Second Article Title", "This is the body of the second article");
		this.articleDbService.saveOrUpdateArticle(article2);
	}
	
	@AfterEach
	void tearDown() {
		this.articleDbService.getAllArticles().forEach(article -> {
			this.articleDbService.deleteArticle(article.getArticleId());
		});
		this.articleId = null;
	}

	@Test
	void testGetAllArticles() {
		List<Article> articles = this.articleDbService.getAllArticles();
		
		assertNotNull(articles);
		assertEquals(articles.size(), 2);
	}

	@Test
	void testGetArticle() {
		Article article = this.articleDbService.getArticle(articleId);
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
		Article article = this.articleDbService.getAllArticles().iterator().next();
		assertNotNull(article);
		
		this.articleDbService.deleteArticle(article.getArticleId());
		
		Exception exception = assertThrows(NoSuchElementException.class, () ->{
			this.articleDbService.getArticle(article.getArticleId());
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
