package com.academy.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@SpringBootApplication
//@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT) alternativa alla property
public class MainApplication {
    public static void main(String[] args) {

        System.out.println("App Starting....");
        SpringApplication.run(MainApplication.class, args);
    }
}