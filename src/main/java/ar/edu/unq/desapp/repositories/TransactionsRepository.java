package ar.edu.unq.desapp.repositories;

import ar.edu.unq.desapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.status = 'active'")
    List<Order> findAllActiveOrders();

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.timestamp BETWEEN :fromDate AND :toDate AND o.status = 'ACTIVE'")
    List<Order> findByUserIdAndDateRange(@Param("userId") Long userId,
                                         @Param("fromDate") LocalDateTime fromDate,
                                         @Param("toDate") LocalDateTime toDate);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = 'CANCELLED_BY_USER' WHERE o.id = :id")
    void cancelOrder(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = 'INPROCESS' WHERE o.id = :id")
    void fillOrder(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = 'FILLED' WHERE o.id = :id")
    void confirmOrder(Long id);
}
