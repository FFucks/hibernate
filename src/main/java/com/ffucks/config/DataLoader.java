package com.ffucks.config;

import com.ffucks.entities.Item;
import com.ffucks.entities.Order;
import com.ffucks.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(OrderRepository orderRepository) {
        return args -> {
            for (int i = 0; i < 50; i++) {
                Order order = new Order();
                order.setOrderNumber("ORD-" + (1000 + i));
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

            System.out.println("DataLoader: populated 50 orders x 5 items");
        };
    }
}
