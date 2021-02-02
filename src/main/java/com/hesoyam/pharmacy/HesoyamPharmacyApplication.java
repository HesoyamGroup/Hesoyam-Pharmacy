package com.hesoyam.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HesoyamPharmacyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HesoyamPharmacyApplication.class, args);
    }

}
