package com.codewithmosh.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {
    private PaymentService payService;
    @Autowired
    public OrderService(PaymentService payService) {
        this.payService = payService;
    }
    public void placeOrder() {
        payService.processPayment(10);
    }
}
