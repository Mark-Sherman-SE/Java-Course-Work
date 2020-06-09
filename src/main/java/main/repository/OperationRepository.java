package main.repository;

import main.entity.Balance;
import main.entity.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OperationRepository extends CrudRepository<Operation, Long> {
    Optional<Operation> findOperationByBalanceId(long id);
    Optional<Operation> findOperationByArticleId(long id);
}
