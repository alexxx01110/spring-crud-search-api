package com.alexm.crud.spring.dao;

import java.util.List;

import com.alexm.crud.spring.model.Article;

public interface ArticleDao {

   long save(Article article);

   Article get(long id);

   List<Article> list();

   void update(long id, Article article);

   void delete(long id);

}
