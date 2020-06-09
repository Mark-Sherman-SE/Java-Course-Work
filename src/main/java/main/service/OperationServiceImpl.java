package main.service;

import main.entity.Operation;
import main.exception.OperationNotFoundException;
import main.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> listOperation() {
        return (List<Operation>) operationRepository.findAll();
    }

    @Override
    public Operation findOperation(long id) {
        Optional<Operation> optionalOperation = operationRepository.findById(id);
        if (optionalOperation.isPresent()) {
            return optionalOperation.get();
        } else {
            throw new OperationNotFoundException("Operation not found");
        }
    }

    @Override
    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public void deleteOperation(long id) {
        try {
            operationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            //ignore it
        }
    }
}
