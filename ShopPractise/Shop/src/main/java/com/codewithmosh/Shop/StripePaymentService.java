package com.codewithmosh.Shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component("stripe")
public class StripePaymentService implements PaymentService  {
    @Value("${stripe.apiUrl}")
    private String apiUrl;
    @Value("${stripe.enabled}")
    private boolean enabled;
    @Value("${stripe.timeout:3000}")
    private int timeout;
    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;
    @Override
    public void processPayment(double amount) {
        System.out.println("STRIPE");
        System.out.println(amount);
        System.out.println(apiUrl);
        System.out.println(enabled);
        System.out.println(timeout);
        System.out.println(supportedCurrencies);
    }
}
