package com.alexm.crud.spring.service;

import java.util.List;

import com.alexm.crud.spring.dao.ArticleDao;
import com.alexm.crud.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImp implements ArticleService {

   @Autowired
   private ArticleDao articleDao;

   @Transactional
   @Override
   public long save(Article article) {
      return articleDao.save(article);
   }

   @Override
   public Article get(long id) {
      return articleDao.get(id);
   }

   @Override
   public List<Article> list() {
      return articleDao.list();
   }

   @Transactional
   @Override
   public void update(long id, Article article) {
      articleDao.update(id, article);
   }

   @Transactional
   @Override
   public void delete(long id) {
      articleDao.delete(id);
   }

}
