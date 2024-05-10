package com.example.registration.course.services;

import com.example.registration.course.dtos.CourseDto;
import com.example.registration.course.entities.Course;
import com.example.registration.course.repositories.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public CourseDto saveCourse(CourseDto courseDto){
        Course course=modelMapper().map(courseDto,Course.class);
       course= courseRepository.save(course);
        courseDto=modelMapper().map(course,CourseDto.class);
        return courseDto;


    }

    public List<Course> searchCourse(String courseName){
        List<Course> courses=courseRepository.getCourseByKeyword(courseName);
        return courses;
    }

    public List<Course> getAllCourses(){
        List<Course> courses=courseRepository.findAll();
        return courses;
    }


    public Course getCourseById(int courseCode){
        return courseRepository.getById(courseCode);
    }






}
