package com.alireza.service;


import com.alireza.model.Article;
import com.alireza.repository.ArticleRepository;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleRepository();

    public void showAllPublishedArticle(){
        System.out.println(articleRepository.showAllPublishedArticle());
    }
    public void showMyArticle(String username){
        System.out.println(articleRepository.showMyArticle(username));
    }
    public void showDetailArticle(String title){
        System.out.println(articleRepository.showDetailArticle(title));
    }

    public void editMyArticle(String username, int id, Article article){
        ArticleRepository.updateArticle(username, id, article);
    }

    public void addNewArticle(int id, Article article){
        ArticleRepository.createArticle(id,article);
    }

    public void editPublishedArticle(String username, String title, boolean is_published){
        ArticleRepository.updatePublishedArticle(username, title, is_published);
    }
}
