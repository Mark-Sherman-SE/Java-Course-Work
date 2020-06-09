package main.controller;

import main.entity.Article;
import main.entity.Balance;
import main.entity.Operation;
import main.exception.OperationNotFoundException;
import main.service.ArticleService;
import main.service.BalanceService;
import main.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class OperationController {
    private ArticleService articleService;
    private BalanceService balanceService;
    private OperationService operationService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/operation")
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> list = operationService.listOperation();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/operation/{id}")
    public ResponseEntity<Operation> getOperation(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(operationService.findOperation(id), HttpStatus.OK);
        } catch (OperationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Operation not found");
        }
    }

    @PostMapping(value = "/operation", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Operation addOperation(@RequestBody Operation newOperation) {
        return operationService.addOperation(newOperation);
    }

    @PutMapping(value = "/operation/{id}", consumes = "application/json")
    public Operation updateOperation(@PathVariable("id") long id, @RequestBody Operation operation) {
        Article article = articleService.findArticle(operation.getArticle().getId());
        Balance balance = balanceService.findBalance(operation.getBalance().getId());
        Optional<Operation> optionalOperation = Optional.of(operationService.findOperation(id));
        return optionalOperation
                .map(op -> {
                    op.setArticle(article);
                    op.setBalance(balance);
                    op.setCreate_date(operation.getCreate_date());
                    op.setCredit(operation.getCredit());
                    op.setDebit(operation.getDebit());
                    return operationService.addOperation(op);
                })
                .orElseGet(() -> {
                    operation.setId(id);
                    return operationService.addOperation(operation);
                });
    }

    @DeleteMapping("/operation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOperation(@PathVariable("id") long id) {
        try {
            operationService.deleteOperation(id);
        } catch (EmptyResultDataAccessException exception) {
            //ignore it
        }
    }

}
