package main.service;

import main.entity.Balance;
import main.exception.BalanceNotFoundException;
import main.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public List<Balance> listBalance() {
        return (List<Balance>) balanceRepository.findAll();
    }

    @Override
    public Balance findBalance(long id) {
        Optional<Balance> optionalBalance = balanceRepository.findById(id);
        if (optionalBalance.isPresent()) {
            return optionalBalance.get();
        } else {
            throw new BalanceNotFoundException("Balance not found");
        }
    }

    @Override
    public Balance addBalance(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public void deleteBalance(long id) {
        balanceRepository.deleteById(id);
    }
}
