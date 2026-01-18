package com.example.makemytrip.service.impl;

import com.example.makemytrip.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final Random random = new Random();
    public boolean ProcessPayment(){
        return random.nextBoolean();
    }
}
