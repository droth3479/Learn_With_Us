package com.example.learn_with_us.data.service;

import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepo;

    public User findUser(String username){
        return userRepo.findByUsername(username);
    }
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }
}
