package com.example.learn_with_us.data.service;

import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repo;

    public List<Course> findAll(){
        return repo.findAll();
    }
}
