package com.example.registration.student.controllers;

import com.example.registration.course.entities.Course;
import com.example.registration.course.services.CourseService;
import com.example.registration.student.dtos.Profile;
import com.example.registration.student.dtos.StudentDto;
import com.example.registration.student.entities.Student;
import com.example.registration.student.repositories.StudentRepository;
import com.example.registration.student.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @PostMapping(value = "/saveStudent")
    public ResponseEntity<String> saveStudent( @RequestBody StudentDto studentDto){

        String message = "";
        HttpStatus status=null;
       StudentDto studentDto1=studentService.saveStudent(studentDto);
       if(studentDto1!=null){
           message = "Registration successful!";
           status = HttpStatus.OK;
       }else{
           message = "Some error occured while registration";
           status = HttpStatus.BAD_REQUEST;
       }
        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestParam int courseCode, @RequestParam String day,Authentication principal) {
        boolean registrationStatus = studentService.courseRegistration(courseCode, day, principal);
        String message = "";
        HttpStatus status;
try {
    if (registrationStatus) {
        message = "Registration successful!";
        status = HttpStatus.OK;
    } else {
        message = "Registration failed. ";

        Course course = courseService.getCourseById(courseCode);

        if (course != null && course.getCapacity() <= 0) {
            message += "Capacity of the course is full. Please choose another course.";
        } else {
            message += "The selected day (" + day + ") is not available for this course. Please choose another day.";
        }
        status = HttpStatus.BAD_REQUEST;
    }
}catch (Exception e){
    return new ResponseEntity<>("some error occured",HttpStatus.BAD_GATEWAY);
}
        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/getStudent")
    public Profile getProfile(Authentication principal){
        Profile profile=studentService.profile(principal);
      System.out.println(profile +"profile");
        return studentService.profile(principal);
    }

    @GetMapping("/student/{id}")
    public  Student getById(@PathVariable int id){
        Student student=studentService.getStudentById(id);
        return student;
    }

    @GetMapping("/unregister")
    public ResponseEntity<String> unregister(Authentication principal) {

        String message = "";
        HttpStatus status;
        Student student = (Student) principal.getPrincipal();
        Course course = student.getCourse();
        if (course == null) {
            message = "You are not registered to any course";
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body("{\"message\": \"" + message + "\"}");
        }
        boolean bool=studentService.unregister(principal);
        if (bool) {
            message = "Unregistered successfully";
            status = HttpStatus.OK;
        } else {
            message = "Some error occurred while unregistering";
            status = HttpStatus.BAD_REQUEST;
        }
        // Return a JSON response with the message and status code
        return ResponseEntity.status(status).body("{\"message\": \"" + message + "\"}");
    }






}
