package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.model.Order;
import ar.edu.unq.desapp.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Transactional
@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<Order> getAllOrders() { return transactionsRepository.findAllActiveOrders(); }

    public List<Order> getOrdersByUserAndDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        return transactionsRepository.findByUserIdAndDateRange(userId, fromDate.atStartOfDay(), toDate.atTime(23, 59, 59));
    }

    public Order createOrder(Order order) {return transactionsRepository.save(order); }
}
