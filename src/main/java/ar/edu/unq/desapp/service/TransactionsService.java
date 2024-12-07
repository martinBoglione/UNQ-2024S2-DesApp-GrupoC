package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.model.Order;
import ar.edu.unq.desapp.model.User;
import ar.edu.unq.desapp.model.dto.OrderRequestDTO;
import ar.edu.unq.desapp.model.exceptions.UserNotFoundException;
import ar.edu.unq.desapp.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Order getOrderById(Long id) {
        return transactionsRepository.findOrderById(id);
    }

    public List<Order> getAllOrders() { return transactionsRepository.findAllActiveOrders(); }

    public List<Order> getOrdersByUserAndDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        return transactionsRepository.findByUserIdAndDateRange(userId, fromDate.atStartOfDay(), toDate.atTime(23, 59, 59));
    }

    public Order createOrder(Order order) {return transactionsRepository.save(order); }

    public void userCancelOrder(Long id) { transactionsRepository.userCancelOrder(id);}

    public void systemCancelOrder(Long id) { transactionsRepository.systemCancelOrder(id);}

    public void fillOrder(Long id) { transactionsRepository.fillOrder(id);}

    public void confirmOrder(Long id) { transactionsRepository.confirmOrder(id);}
}
