package com.vlad.repin.alpha.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphaBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaBankApplication.class, args);
    }
}
