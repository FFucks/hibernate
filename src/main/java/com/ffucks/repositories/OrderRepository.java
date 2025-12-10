package com.ffucks.repositories;

import com.ffucks.entities.Order;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    public List<Order> OrderRepositoryN1(EntityManager em) {
        List<Order> orders = em.createQuery("SELECT o from Order o", Order.class).getResultList();
        for (Order o : orders) {
            System.out.println(o.getItems().size());
        }

        return orders;
    }

    public List<Order> OrderRepositoryJoinFetch(EntityManager em) {
        List<Order> orders = em.createQuery("select o from Order o join fetch o.items", Order.class).getResultList();
        return orders;
    }



}
