package main.service;

import main.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> listArticles();
    Article findArticle(long id);
    Article addArticle(Article article);
    void deleteArticle(long id);
}
