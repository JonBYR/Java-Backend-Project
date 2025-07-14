package com.codewithmosh.Shop;

import com.codewithmosh.Shop.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ShopApplication.class, args);
		var orderService = context.getBean(OrderService.class);
		orderService.placeOrder();
		var address = Address.builder()
				.street("1 Severn Street")
				.city("Lincoln")
				.postcode("LN1 1SJ")
				.build();
		User user = new User();
		user.setId(1L);
		user.setEmail("blank");
		user.setPassword("blank2");
		user.setName("Someone");
		user.AddTags("user_1");
		var profile = new Profile(1L, "Just a guy", "0777565655", "31st October 2000", 23, user);
		user.setProfile(profile);
		Category category = new Category();
		category.setId(1L);
		category.setName("Fruits");
		Product product = new Product(1L, "Pineapple", new BigDecimal("0.99"), category);
		category.AddProduct(product);
		System.out.println(category);
	}

}
