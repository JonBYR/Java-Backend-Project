package com.codewithmosh.Shop;

import org.springframework.stereotype.Component;

//@Component("paypal")
public class PaypalPaymentService implements PaymentService{
    @Override
    public void processPayment(double amount) {
        System.out.println("PAYPAL");
        System.out.println(amount);
    }
}
