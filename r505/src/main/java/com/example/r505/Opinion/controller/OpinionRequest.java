package com.example.r505.Opinion.controller;

public class OpinionRequest {
    private String articleId;
    private String userId;
    private Boolean liked;
    public String getArticleId() {
        return articleId;
    }
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Boolean getLiked() {
        return liked;
    }
    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
