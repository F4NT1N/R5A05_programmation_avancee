package com.example.r505.Opinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.r505.Article.model.Article;
import com.example.r505.Article.model.ArticleRepository;
import com.example.r505.User.model.User;
import com.example.r505.User.model.UserRepository;
import com.example.r505.Opinion.model.Opinion;
import com.example.r505.Opinion.model.OpinionRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@RequestMapping(path="/opinions")
public class OpinionController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OpinionRepository opinionRepository;

    @PostMapping
    public ResponseEntity<String> addNewOpinion(@RequestBody OpinionRequest request) {
        
        if (request == null || request.getArticleId() == null || request.getUserId() == null || request.getLiked() == null) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
        Article article = articleRepository.findById(Integer.parseInt(request.getArticleId())).orElse(null);
        if (article == null) {
            return ResponseEntity.status(404).body("Article not found, cannot save opinion.");
        }
        User user = userRepository.findById(Integer.parseInt(request.getUserId())).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found, cannot save opinion.");
        }

        Opinion n = new Opinion();
        n.setArticle(article);
        n.setUser(user);
        n.setLiked(request.getLiked());
        opinionRepository.save(n);
    
        return ResponseEntity.status(201).body("Opinion saved successfully.");
    }

    @GetMapping
    public @ResponseBody Iterable<Opinion> getAllArticles() {
        return opinionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opinion> getUserById(@PathVariable Integer id) {
        return opinionRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody Boolean liked) {
        
        Opinion n = opinionRepository.findById(Integer.parseInt(id)).orElse(null);
        if (n == null) {
            return ResponseEntity.status(404).body("Opinion not found.");
        }

        n.setLiked(liked);
        opinionRepository.save(n);
        return ResponseEntity.ok("Opinion updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpinion(@PathVariable Integer id) {
        if (!opinionRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Opinion not found.");
        }
        opinionRepository.deleteById(id);
        return ResponseEntity.ok("Opinion deleted successfully.");
    }
    
}
