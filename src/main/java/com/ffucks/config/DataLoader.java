package com.ffucks.config;

import com.ffucks.entities.*;
import com.ffucks.repositories.EmployeeRepository;
import com.ffucks.repositories.OrderRepository;
import com.ffucks.repositories.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(OrderRepository orderRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                Order order = new Order();
                order.setOrderNumber("Order-" + (1000 + i));
                order.setStatus("NEW");
                order.setTotalAmount(BigDecimal.valueOf(100 + i));

                for (int j = 0; j < 5; j++) {
                    Item item = new Item();
                    item.setName("Item-" + i + "-" + j);
                    item.setPrice(10.0f + j);
                    item.setQuantity(j + 1);
                    order.addItem(item);
                }

                orderRepository.save(order);
            }

            System.out.println("DataLoader: populated 10 orders x 5 items");
        };
    }

    /*@Bean
    CommandLineRunner loadData(StoreRepository storeRepository, EmployeeRepository employeeRepository) {
        return args -> {

            if (storeRepository.count() > 0) {
                System.out.println("DataLoader: DB already populated, skipping.");
                return;
            }

            Store store = new Store();
            store.setName("Supermarket Central");
            store.setAddress("123 Main St, Anytown");
            store.setPhoneNumber("555-1234");
            store = storeRepository.save(store);

            for (int i = 1; i <= 20; i++) {
                Employee e = new Employee();
                e.setName("Employee " + i);
                e.setStore(store);
                employeeRepository.save(e);
            }

            System.out.println("DataLoader: populated Store with 20 employees, Store id: " + store.getId());
        };
    }*/
}
