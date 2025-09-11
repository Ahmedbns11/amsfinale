package com.sip.controllers;

import com.sip.entities.Article;
import com.sip.entities.ArticleDTO;
import com.sip.entities.ArticleDTORecord;
import com.sip.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {

	@Autowired
	private ArticleService articleService;

	// Get all articles
	@GetMapping
	public List<Article> getAllArticles() {
		return articleService.getAllArticles();
	}

	@GetMapping("/articlesDto")
	public List<ArticleDTORecord> getAllArticlesDTO() {
		return articleService.getAllArticlesWithDTO();
	}

	
	// Get a single article by ID
	@GetMapping("/{id}")
	public Article getArticleById(@PathVariable Long id) {
		return articleService.getArticleById(id);
	}

	// Create a new article
	@PostMapping("/{providerId}")
	Article createArticle(@PathVariable(value = "providerId") Long providerId, @Valid @RequestBody Article article) {
		return articleService.createArticle(providerId, article);
	}


	// Update an existing article
	@PutMapping("/{id}")
	public Article updateArticle(@PathVariable Long id, @Valid @RequestBody Article article) {
		return articleService.updateArticle(id, article);
	}

	// Delete an article by ID
	@DeleteMapping("/{id}")
	public Article deleteArticle(@PathVariable Long id) {
		return articleService.deleteArticle(id);
	}
}
