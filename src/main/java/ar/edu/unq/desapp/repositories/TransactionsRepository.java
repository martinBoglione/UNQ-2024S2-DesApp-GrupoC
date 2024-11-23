package ar.edu.unq.desapp.repositories;

import ar.edu.unq.desapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Order,Long> {
}
