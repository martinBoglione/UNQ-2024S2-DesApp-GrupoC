package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.model.Order;
import ar.edu.unq.desapp.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Order createOrder(Order order) {
        return transactionsRepository.save(order);
    }
}
