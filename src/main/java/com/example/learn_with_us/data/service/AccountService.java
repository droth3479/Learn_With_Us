package com.example.learn_with_us.data.service;

import com.example.learn_with_us.data.entity.Role;
import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.repository.RoleRepository;
import com.example.learn_with_us.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    public User findUser(String username){
        return userRepo.findByUsername(username);
    }
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }
    public User validateUser(String username, String password){
        return userRepo.findByUsernameAndPassword(username, password);
    }

    public List<Role> findAllRoles() {return roleRepo.findAll();}
    public void updateUser(User user){
        userRepo.save(user);
    }
}
