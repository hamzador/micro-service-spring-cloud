package org.dr.orderservice;

import org.dr.orderservice.entity.Order;
import org.dr.orderservice.entity.ProductItem;
import org.dr.orderservice.enums.OrderStatus;
import org.dr.orderservice.model.Costomer;
import org.dr.orderservice.model.Product;
import org.dr.orderservice.repository.OrderRepository;
import org.dr.orderservice.repository.ProductItemRepository;
import org.dr.orderservice.service.CostomerRestClientService;
import org.dr.orderservice.service.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CostomerRestClientService costomerRestClientService,
							InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Costomer> customers = costomerRestClientService.allCustomers().getContent().stream().toList();
			List<Product> products = inventoryRestClientService.allProduct().getContent().stream().toList();

			Random random = new Random();

			for (int i = 0; i < 20; i++){
				Order order = Order.builder().customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.5? OrderStatus.CREATED:OrderStatus.PENDING)
						.createdAt(new Date())
						.build();
				Order savedOrder = orderRepository.save(order);
				for(int j =0; j < products.size(); j++){
					if(Math.random()>0.70){
						ProductItem productItem = ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.price(products.get(j).getPrice())
								.quantity(1+ random.nextInt(10))
								.discount( Math.random())
								.build();
						productItemRepository.save(productItem);
					}
				}
			}


		};
	}

}
