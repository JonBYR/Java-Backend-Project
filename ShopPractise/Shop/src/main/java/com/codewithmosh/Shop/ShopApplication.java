package com.codewithmosh.Shop;

import com.codewithmosh.Shop.entities.*;
import com.codewithmosh.Shop.repositories.TagRepository;
import com.codewithmosh.Shop.repositories.UserRepository;
import com.codewithmosh.Shop.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ShopApplication.class, args);

		var repository = context.getBean(UserService.class);
		var tagRepo = context.getBean(TagRepository.class);
		var address = Address.builder()
				.street("1 Severn Street")
				.city("Lincoln")
				.postcode("LN1 1SJ")
				.build();
		repository.ShowEntityStates();
		Category category = new Category();
		category.setName("Fruits");
		Product product = new Product(1L, "Pineapple", new BigDecimal("0.99"), category, null);
		category.AddProduct(product);
		System.out.println(category);
	}

}
