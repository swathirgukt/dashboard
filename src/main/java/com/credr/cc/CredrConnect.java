package com.credr.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

//@EnableScheduling
public class CredrConnect {
    public static void main(String[] args) {
        SpringApplication.run(CredrConnect.class, args);
        System.out.println("Started CredrConnect Application.");
    }
}
