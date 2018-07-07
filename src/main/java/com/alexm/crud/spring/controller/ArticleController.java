package com.alexm.crud.spring.controller;

import java.util.List;

import com.alexm.crud.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alexm.crud.spring.service.ArticleService;
import com.alexm.crud.spring.service.HibernateSearchService;

@CrossOrigin(origins = { "http://localhost:8081" }, maxAge = 6000)
@RestController
public class ArticleController {

   @Autowired
   private ArticleService articleService;

   @Autowired
   private HibernateSearchService hibernateSearchService;

   /*---Add new article---*/
   @PostMapping("/article")
   public ResponseEntity<?> save(@RequestBody Article article) {
      long id = articleService.save(article);
      return ResponseEntity.ok().body("New Article has been saved with ID:" + id);
   }

   /*---Get a article by id---*/
   @GetMapping("/article/{id}")
   public ResponseEntity<Article> get(@PathVariable("id") long id) {
      Article article = articleService.get(id);
      return ResponseEntity.ok().body(article);
   }

   /*---Get a article search result---*/
   @GetMapping("/article/search")
   public ResponseEntity<List<Article>> search(@RequestParam(value = "query", required = false) String q) {
      List<Article> article = hibernateSearchService.fuzzySearch(q);
      return ResponseEntity.ok().body(article);
   }

   /*---get all articles---*/
   @GetMapping("/article")
   public ResponseEntity<List<Article>> list() {
      List<Article> articles = articleService.list();
      return ResponseEntity.ok().body(articles);
   }

   /*---Update a article by id---*/
   @PutMapping("/article/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Article article) {
      articleService.update(id, article);
      return ResponseEntity.ok().body("Article has been updated successfully.");
   }

   /*---Delete a article by id---*/
   @DeleteMapping("/article/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      articleService.delete(id);
      return ResponseEntity.ok().body("Article has been deleted successfully.");
   }
}