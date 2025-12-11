package com.ffucks.controllers;

import com.ffucks.entities.Order;
import com.ffucks.services.OrderService;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final EntityManager entityManager;

    public OrderController(OrderService orderService, EntityManager entityManager) {
        this.orderService = orderService;
        this.entityManager = entityManager;
    }

    private void clearHibernateStats() {
        SessionFactory sf = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        sf.getStatistics().clear();
    }

    private void printHibernateStats(String sqlType) {
        SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();

        System.out.println(sqlType + " - queryExecutionCount: " + stats.getQueryExecutionCount());
        System.out.println(sqlType + " - prepareStatementCount: " + stats.getPrepareStatementCount());
        System.out.println(sqlType + " - entityFetchCount: " + stats.getEntityFetchCount());
        System.out.println(sqlType + " - collectionFetchCount: " + stats.getCollectionFetchCount());

        stats.clear();
    }

    @GetMapping("/nplusone")
    public List<Order> getOrderNPlusOne() {
        clearHibernateStats();

        List<Order> orders = orderService.getOrderRepositoryNPlusOne();
        orders.forEach(o ->
                System.out.println("N + 1 Order item size: " + o.getItems().size()));

        printHibernateStats("After N+1");

        return orders;
    }

    @GetMapping("/orderfetch")
    public List<Order> getOrderFetch() {
        clearHibernateStats();

        List<Order> orders = orderService.getOrderRepositoryFetch();
        orders.forEach(o ->
                System.out.println("Order Fetch Item size: " + o.getItems().size()));

        printHibernateStats("After Fetch");

        return orders;
    }

    @GetMapping("/batch")
    public List<Order> getOrderBatch() {
        clearHibernateStats();

        List<Order> orders = orderService.getOrderRepositoryBatch();

        orders.forEach(o ->
                System.out.println("Order Batch Item size: " + o.getItems().size()));

        printHibernateStats("After Batch");

        return orders;
    }


}
