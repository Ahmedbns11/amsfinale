package com.sip.services;

import com.sip.entities.Article;
import com.sip.entities.ArticleDTO;
import com.sip.entities.ArticleDTORecord;
import com.sip.repositories.ArticleRepository;
import com.sip.repositories.ProviderRepository;
import com.sip.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ProviderRepository providerRepository;

	 private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
	 
	// Retrieve all articles
	public List<Article> getAllArticles() {
		//log.info(articleRepository.findAll().toString());
		return (List<Article>) articleRepository.findAll();
	}

	
	// Retrieve all articles with DTO
		public List<ArticleDTORecord> getAllArticlesWithDTO() {
			//log.info(articleRepository.findAll().toString());
			List<ArticleDTORecord> articlesDTO = new ArrayList<>();
			List<Article> temp= (List<Article>) articleRepository.findAll();
			for(Article article : temp)
			{
				/*ArticleDTO ato = new ArticleDTO();
				ato.setId(article.getId());
				ato.setLabel(article.getLabel());
				ato.setPrice(article.getPrice());
				ato.setPicture(article.getPicture());*/
				
				ArticleDTORecord ato = new ArticleDTORecord(article.getId(),article.getLabel(),article.getPrice(),article.getPicture());
				articlesDTO.add(ato);
			}
			return articlesDTO;
		}

		
	// Retrieve an article by ID
	public Article getArticleById(Long id) {
		return articleRepository.findById(id).map(a -> {
			return a;
		}).orElseThrow(() -> new ResourceNotFoundException("Article with Id " + id + " was not found"));
	}

	// Save a new article
	public Article createArticle(Long providerId, Article article) {
		return providerRepository.findById(providerId).map(provider -> {
			article.setProvider(provider);
			return articleRepository.save(article);
		}).orElseThrow(() -> new ResourceNotFoundException("ProviderId " + providerId + " not found"));
	}

	// Update an existing article
	public Article updateArticle(Long id, Article newArticle) {
		return articleRepository.findById(id).map(existingArticle -> {
			existingArticle.setLabel(newArticle.getLabel());
			existingArticle.setPrice(newArticle.getPrice());
			existingArticle.setPicture(newArticle.getPicture());
			existingArticle.setProvider(newArticle.getProvider());
			return articleRepository.save(existingArticle);
		}).orElseThrow(() -> new ResourceNotFoundException("Article with Id " + id + " was not found"));
	}

	// Delete an article by ID
	public Article deleteArticle(Long id) {
		return articleRepository.findById(id).map(a -> {
			articleRepository.delete(a);
			return a;
		}).orElseThrow(() -> new ResourceNotFoundException("Article with Id " + id + " was not found"));
	}
}
