package com.alexm.crud.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.alexm.crud.spring.model.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoImp implements ArticleDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public long save(Article article) {
      sessionFactory.getCurrentSession().save(article);
      return article.getId();
   }

   @Override
   public Article get(long id) {
      return sessionFactory.getCurrentSession().get(Article.class, id);
   }

   @Override
   public List<Article> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Article> cq = cb.createQuery(Article.class);
      Root<Article> root = cq.from(Article.class);
      cq.select(root);
      Query<Article> query = session.createQuery(cq);
      return query.getResultList();
   }

   @Override
   public void update(long id, Article article) {
      Session session = sessionFactory.getCurrentSession();
      Article article2 = session.byId(Article.class).load(id);
      article2.setTitle(article.getTitle());
      article2.setText(article.getText());
      session.flush();
   }

   @Override
   public void delete(long id) {
      Session session = sessionFactory.getCurrentSession();
      Article article = session.byId(Article.class).load(id);
      session.delete(article);
   }

}
