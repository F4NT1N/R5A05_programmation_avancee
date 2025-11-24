package com.example.r505.Article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.r505.Article.model.Article;
import com.example.r505.Article.model.ArticleRepository;
import com.example.r505.User.model.User;
import com.example.r505.User.model.UserRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping(path="/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> addNewArticle(@RequestBody ArticleRequest request) {
        
        if (request == null || request.getContent() == null || request.getAuthorId() == null) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

        User author = userRepository.findById(request.getAuthorId()).orElse(null);
        if (author == null) {
            return ResponseEntity.status(404).body("Author not found, cannot save article.");
        }

        Article n = new Article();
        n.setContent(request.getContent());
        n.setAuthor(author);
        articleRepository.save(n);

        return ResponseEntity.status(201).body("Saved");
    }

    @GetMapping
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody ArticleRequest request) {

        if (request == null || request.getContent() == null || request.getAuthorId() == null) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

        User author = userRepository.findById(request.getAuthorId()).orElse(null);
        if (author == null) {
            return ResponseEntity.status(404).body("Author not found, cannot save article.");
        }
        
        Article n = articleRepository.findById(Integer.parseInt(id)).orElse(null);
        if (n == null) {
            return ResponseEntity.status(404).body("Article not found.");
        }
        n.setContent(request.getContent());
        n.setAuthor(author);
        articleRepository.save(n);

        return ResponseEntity.ok("Article updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleById(@PathVariable Integer id) {
        if (!articleRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Article not found.");
        }
        articleRepository.deleteById(id);
        return ResponseEntity.ok("Article deleted successfully.");
    }
}
