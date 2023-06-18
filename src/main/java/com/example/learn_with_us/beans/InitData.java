package com.example.learn_with_us.beans;

import com.example.learn_with_us.data.entity.Role;
import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.service.AccountService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.PostConstruct;
import org.atmosphere.config.service.Singleton;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@Singleton
public class InitData {
    @Autowired
    AccountService service;

    @PostConstruct
    public void init() {
        Role admin = new Role("ADMINISTRATOR");
        Role standard = new Role("REGULAR");
        service.addRole(admin);
        service.addRole(standard);

        service.addUser(new User(
                "Regular",
                "secret",
                standard
        ));

        service.addUser(new User(
                "Administrator",
                "secret",
                admin
        ));
    }
}
