package com.alexm.crud.spring.service;

import java.util.List;

import com.alexm.crud.spring.model.Article;

public interface ArticleService {

   long save(Article article);
   Article get(long id);
   List<Article> list();
   void update(long id, Article article);
   void delete(long id);
}
