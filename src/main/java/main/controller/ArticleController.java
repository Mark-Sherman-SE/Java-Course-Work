package main.controller;

import main.entity.Article;
import main.entity.Operation;
import main.exception.ArticleNotFoundException;
import main.repository.ArticleRepository;
import main.repository.OperationRepository;
import main.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class ArticleController {

    private ArticleService articleService;

    private OperationRepository operationRepository;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @GetMapping("/article")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = articleService.listArticles();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(articleService.findArticle(id), HttpStatus.OK);
        } catch (ArticleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
    }

    @PostMapping(value = "/article", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Article addArticle(@RequestBody Article newArticle) {
        return articleService.addArticle(newArticle);
    }

    @PutMapping(value = "/article/{id}", consumes = "application/json")
    public Article updateArticle(@PathVariable("id") long id, @RequestBody Article article) {
        Optional<Article> optionalArticle = Optional.of(articleService.findArticle(id));
        return optionalArticle
                .map(art -> {
                    art.setName(article.getName());
                    return articleService.addArticle(art);
                })
                .orElseGet(() -> {
                    article.setId(id);
                    return articleService.addArticle(article);
                });
    }

    @DeleteMapping("/article/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable("id") long id) {
        try {
            Optional<Operation> operation = operationRepository.findOperationByArticleId(id);
            if (operation.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Such article is related to operations table");
            } else {
                articleService.deleteArticle(id);
            }
        } catch (EmptyResultDataAccessException exception) {
            //ignore it
        }
    }
}
