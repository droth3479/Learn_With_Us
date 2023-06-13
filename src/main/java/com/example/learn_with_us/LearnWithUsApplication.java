package com.example.learn_with_us;

import com.example.learn_with_us.data.entity.*;
import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.repository.CourseRepository;
import com.example.learn_with_us.data.service.ContentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class LearnWithUsApplication {
    @Autowired
    ContentService service = new ContentService();

    public static void main(String[] args) {
        SpringApplication.run(LearnWithUsApplication.class, args);
    }
}
