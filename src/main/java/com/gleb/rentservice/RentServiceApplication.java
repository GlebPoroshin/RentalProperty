package com.gleb.rentservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gleb.rentservice.config")
@ComponentScan("com.gleb.rentservice.services")
@ComponentScan("com.gleb.rentservice.repositories")
public class RentServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
