package com.codewithmosh.Shop;

import com.codewithmosh.Shop.entities.*;
import com.codewithmosh.Shop.repositories.TagRepository;
import com.codewithmosh.Shop.repositories.UserRepository;
import com.codewithmosh.Shop.services.ProductService;
import com.codewithmosh.Shop.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ShopApplication.class, args);

		var repository = context.getBean(ProductService.class);
		var userService = context.getBean(UserService.class);
		var tagRepo = context.getBean(TagRepository.class);
		repository.GetProductsBySpecification("Apple", null, null);
	}

}
