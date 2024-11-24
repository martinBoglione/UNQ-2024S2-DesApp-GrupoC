package ar.edu.unq.desapp.repositories;

import ar.edu.unq.desapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.status = 'active'")
    List<Order> findAllActiveOrders();

}
