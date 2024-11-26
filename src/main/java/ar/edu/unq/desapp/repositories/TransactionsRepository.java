package ar.edu.unq.desapp.repositories;

import ar.edu.unq.desapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.status = 'active'")
    List<Order> findAllActiveOrders();

    //TODO: Cambiar ACTIVE por FILLED
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.timestamp BETWEEN :fromDate AND :toDate AND o.status = 'ACTIVE'")
    List<Order> findByUserIdAndDateRange(@Param("userId") Long userId,
                                         @Param("fromDate") LocalDateTime fromDate,
                                         @Param("toDate") LocalDateTime toDate);
}
