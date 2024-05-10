package com.example.registration.course.controller;


import com.example.registration.course.dtos.CourseDto;
import com.example.registration.course.entities.Course;
import com.example.registration.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/saveCourse")
    public CourseDto saveCourse(@RequestBody CourseDto courseDto){
        return  courseService.saveCourse(courseDto);


    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourse(@RequestParam String courseName){
        List<Course> courses= courseService.searchCourse(courseName);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> courses= courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
