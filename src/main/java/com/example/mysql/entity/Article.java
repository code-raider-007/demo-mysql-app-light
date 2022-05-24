package com.example.mysql.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4565232826433154822L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long articleId;
	
	private String title;
	
	private String body;
	
	private String author;

	public Article() {
		super();
	}

	public Article(Long articleId, String title, String body, String author) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
