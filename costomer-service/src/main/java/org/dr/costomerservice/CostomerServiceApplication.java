package org.dr.costomerservice;

import org.dr.costomerservice.entitiy.Costomer;
import org.dr.costomerservice.repository.CostomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CostomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CostomerRepository costomerRepository){
		return args -> {
			costomerRepository.saveAll(List.of(
					Costomer.builder().name("hamza").email("hmz@gmail.com").build(),
					Costomer.builder().name("oussama").email("osm@gmail.com").build(),
					Costomer.builder().name("youssef").email("ysf@gmail.com").build()
			));
			costomerRepository.findAll().forEach(System.out::println);
		};
	}

}
