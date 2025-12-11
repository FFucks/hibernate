package com.ffucks.repositories;

import com.ffucks.entities.Order;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // findAll() pattern of JpaRepository that causes N+1 problem

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.items")
    List<Order> findUsingFetch();

}
