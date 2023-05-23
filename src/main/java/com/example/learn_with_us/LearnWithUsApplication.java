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

    @PostConstruct
    public void init() {
        Course scienceCourse = new Course();
        scienceCourse.setName("Physics");
        scienceCourse.setFounder("Joe Shmoe");
        scienceCourse.setSubject(new Subject("Science"));

        BaseContent scienceVideo = new VideoContent();
        scienceVideo.setSource("learnwithus/content/video/cokeandmentos");

        Class scienceClass = new Class();
        scienceClass.setName("Chemical Reactions");
        scienceClass.setCourse(scienceCourse);
        scienceClass.setContent(scienceVideo);

        service.addCourse(scienceCourse);
        service.addClassContent(scienceVideo);
        service.addClass(scienceClass);
    }
}
