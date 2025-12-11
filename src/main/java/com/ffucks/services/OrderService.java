package com.ffucks.services;

import com.ffucks.entities.Order;
import com.ffucks.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> getOrderRepositoryNPlusOne() {
        return orderRepository.findAll();
    }

    @Transactional
    public List<Order> getOrderRepositoryFetch() {
        return orderRepository.findUsingFetch();
    }

    @Transactional()
    public List<Order> getOrderRepositoryBatch() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(o -> o.getItems().size());
        return orders;
    }
}
