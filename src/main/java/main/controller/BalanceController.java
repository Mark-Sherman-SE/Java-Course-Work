package main.controller;

import main.entity.Balance;
import main.entity.Operation;
import main.exception.BalanceNotFoundException;
import main.repository.OperationRepository;
import main.service.BalanceService;
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
public class BalanceController {

    private BalanceService balanceService;

    private OperationRepository operationRepository;

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @GetMapping("/balance")
    public ResponseEntity<List<Balance>> getAllBalance() {
        List<Balance> list = balanceService.listBalance();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Balance> getBalance(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(balanceService.findBalance(id), HttpStatus.OK);
        } catch (BalanceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Balance not found");
        }
    }

    @PostMapping(value = "/balance", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Balance addBalance(@RequestBody Balance newBalance) {
        return balanceService.addBalance(newBalance);
    }

    @PutMapping(value = "/balance/{id}", consumes = "application/json")
    public Balance updateArticle(@PathVariable("id") long id, @RequestBody Balance balance) {
        Optional<Balance> optionalBalance = Optional.of(balanceService.findBalance(id));
        return optionalBalance
                .map(bal -> {
                    bal.setAmount(balance.getAmount());
                    bal.setCreate_date(balance.getCreate_date());
                    bal.setCredit(balance.getCredit());
                    bal.setDebit(balance.getDebit());
                    return balanceService.addBalance(bal);
                })
                .orElseGet(() -> {
                    balance.setId(id);
                    return balanceService.addBalance(balance);
                });
    }

    @DeleteMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBalance(@PathVariable("id") long id) {
        try {
            Optional<Operation> operation = operationRepository.findOperationByBalanceId(id);
            if (operation.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Such balance is related to operations table");
            } else {
                balanceService.deleteBalance(id);
            }
        } catch (EmptyResultDataAccessException exception) {
            //ignore it
        }
    }

}
