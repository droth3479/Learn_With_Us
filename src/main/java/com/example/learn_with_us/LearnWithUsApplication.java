package com.example.learn_with_us;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class LearnWithUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnWithUsApplication.class, args);
    }

}
