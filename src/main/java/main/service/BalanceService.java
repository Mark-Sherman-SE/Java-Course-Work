package main.service;

import main.entity.Balance;

import java.util.List;

public interface BalanceService {
    List<Balance> listBalance();
    Balance findBalance(long id);
    Balance addBalance(Balance balance);
    void deleteBalance(long id);
}
