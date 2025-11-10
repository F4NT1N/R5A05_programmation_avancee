package com.example.r505.Opinion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import com.example.r505.Article.model.Article;
import com.example.r505.User.model.User;

@Entity
@Table(name = "opinions", 
uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "article_id"}))
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(nullable = false)
    private Boolean liked; // true for like, false for dislike

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }
    public Boolean getLiked() {
        return liked;
    }
    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
