package com.ffucks.config;

import com.ffucks.entities.*;
import com.ffucks.repositories.ClientRepository;
import com.ffucks.repositories.EmployeeRepository;
import com.ffucks.repositories.OrderRepository;
import com.ffucks.repositories.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    /*@Bean
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

            System.out.println("DataLoader: populated 3 orders x 10 items");
        };
    }*/

    @Bean
    CommandLineRunner loadData(StoreRepository storeRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        return args -> {

            if (storeRepository.count() > 0) {
                System.out.println("DataLoader: DB already populated, skipping.");
                return;
            }

            Store store = new Store();
            store.setName("Supermarket Central");
            store = storeRepository.save(store);

            for (int i = 1; i <= 20; i++) {
                Employee e = new Employee();
                e.setName("Employee " + i);
                e.setStore(store);
                employeeRepository.save(e);
            }

            for (int i = 1; i <= 30; i++) {
                Client c = new Client();
                c.setName("Client " + i);
                c.setStore(store);
                clientRepository.save(c);
            }

            System.out.println("DataLoader: populated Store with 20 employees and 30 clients, Store id: " + store.getId());
        };
    }
}
