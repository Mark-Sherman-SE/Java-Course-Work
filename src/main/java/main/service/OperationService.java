package main.service;

import main.entity.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> listOperation();
    Operation findOperation(long id);
    Operation addOperation(Operation operation);
    void deleteOperation(long id);
}
