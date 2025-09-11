package com.sip.repositories;

import com.sip.entities.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    // Additional query methods can be defined here if needed
}
