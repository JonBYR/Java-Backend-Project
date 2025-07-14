package com.codewithmosh.Shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${spring.application.service}")
    private String service;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }
    @Bean
    public PaymentService paypal() {
        return new PaypalPaymentService();
    }

    @Bean
    //if you want this to run lazy, use @Lazy here
    public OrderService orderService() {
        if (service.equals("stripe")) {
            return new OrderService(stripe());
        }
        else
        {
            return new OrderService(paypal());
        }
    }
    /*
    @Bean
    public InMemoryUserRepository inMemoryUserRepository() {
        return new InMemoryUserRepository();
    }
    @Bean
    public EmailNotificationService emailNotificationService() {
        return new EmailNotificationService();
    }
    @Bean
    public UserService userService() {
        return new UserService(emailNotificationService(), inMemoryUserRepository());
    }
     */
}
