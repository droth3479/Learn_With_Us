package com.example.learn_with_us.data.service;

import com.example.learn_with_us.data.entity.*;
import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.repository.ClassContentRepository;
import com.example.learn_with_us.data.repository.ClassRepository;
import com.example.learn_with_us.data.repository.CourseRepository;
import com.example.learn_with_us.data.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The {@code ContentService} class is a Facade class that provides service methods for managing courses, classes, and
 * class content.
 * It interacts with the {@code CourseRepository}, {@code ClassRepository}, and {@code ClassContentRepository} to
 * perform CRUD operations.
 */
@Service
@Transactional
public class ContentService {
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private ClassRepository classRepo;
    @Autowired
    private ClassContentRepository classContentRepo;
    @Autowired
    private SubjectRepository subjectRepo;

    public List<Course> findAllCourses(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return courseRepo.findAll();
        } else {
            return courseRepo.search(stringFilter);
        }
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

    public List<Course> findAllCourseByUserId(User user) {
        return courseRepo.findCourseByUser(user);
    }

    public void deleteCourse(Course course){
        List<Class> allClasses = getClassesInCourse(course);
        for(Class c : allClasses) {
            deleteClass(c);
        }
        courseRepo.deleteCourseById(course.getId());
    }

    public void deleteClass(Class c) {
        BaseContent content = c.getContent();
        classRepo.deleteClassById(c.getId());
        deleteClassContent(content);
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

    /**
     * Gets all classes that are joined to the provided course entity.
     * @param course The course which will be used as a join.
     * @return All classes whose course_id joins with the courseName param.
     */
    public List<Class> getClassesInCourse(Course course) {
        return classRepo.findByCourse(course);
    }

    public List<Class> findAllClassesByUser(User user){
        return classRepo.findByUser(user);
    }

    public void addClassContent(BaseContent bc){
        classContentRepo.save(bc);
    }

    public void deleteClassContent(BaseContent bc){
        classContentRepo.deleteById(bc.getId());
    }

    public void addSubject(Subject s){
        subjectRepo.save(s);
    }

    public List<Subject> getSubject(String name){
        return subjectRepo.findSubjectByName(name);
    }

    public List<Subject> findAllSubjects() {
        return subjectRepo.findAll();
    }
}
