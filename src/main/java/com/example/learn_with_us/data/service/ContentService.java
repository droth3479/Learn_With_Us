package com.example.learn_with_us.data.service;

import com.example.learn_with_us.data.entity.BaseContent;
import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.repository.ClassContentRepository;
import com.example.learn_with_us.data.repository.ClassRepository;
import com.example.learn_with_us.data.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The {@code ContentService} class is a Facade class that provides service methods for managing courses, classes, and
 * class content.
 * It interacts with the {@code CourseRepository}, {@code ClassRepository}, and {@code ClassContentRepository} to
 * perform CRUD operations.
 */
@Service
public class ContentService {
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private ClassRepository classRepo;
    @Autowired
    private ClassContentRepository classContentRepo;

    public List<Course> findAll(){
        return courseRepo.findAll();
    }

    public void addCourse(Course course) {
        courseRepo.save(course);
    }

    /**
     * Gets the Course object matching the provided name.
     * @param name Name of the requested course.
     * @return A course object matching the parameters.
     */
    public Course getCourse(String name) {
        return courseRepo.findCourseByName(name).get(0);
    }

    public void addClass(Class c) {
        classRepo.save(c);
    }

    /**
     * Gets the Class object matching the provided class name and course name fields.
     * @param className Name of the requested class.
     * @param courseName Name of the course of the requested class.
     * @return A class object matching the parameters.
     */
    public Class getClass(String className, String courseName){
        return classRepo.findByNameAndCourse(className,
            getCourse(courseName)).get(0);
    }

    public void addClassContent(BaseContent bc){
        classContentRepo.save(bc);
    }
}
