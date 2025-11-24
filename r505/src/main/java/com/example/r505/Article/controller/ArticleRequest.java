package com.example.r505.Article.controller;

public class ArticleRequest {
    private String content;
    private Integer authorId;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
