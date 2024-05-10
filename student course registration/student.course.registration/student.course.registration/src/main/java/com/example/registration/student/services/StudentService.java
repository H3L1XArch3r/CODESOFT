package com.example.registration.student.services;

import com.example.registration.course.entities.Course;
import com.example.registration.course.repositories.CourseRepository;
import com.example.registration.student.dtos.Profile;
import com.example.registration.student.dtos.StudentDto;
import com.example.registration.student.entities.Student;
import com.example.registration.student.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

      public StudentDto saveStudent(StudentDto studentDto){


        Student student=this.modelMapper().map(studentDto,Student.class);
        student.setRole("ROLE_USER");

        student=studentRepository.save(student);
          studentDto=this.modelMapper().map(student,StudentDto.class);
        return  studentDto;
    }

    public boolean courseRegistration(int courseCode,String day,Authentication principal){
        try {
            int capacity = courseRepository.getCapacity(courseCode);
            String schedule= courseRepository.getSchedule(courseCode);


            if (capacity > 0 && schedule.equals(day.toLowerCase())) {
                Course course = courseRepository.getById(courseCode);
                Student student= (Student) principal.getPrincipal();
                student.setCourse(course);
                studentRepository.save(student);
                capacity = capacity - 1;
                course.setCapacity(capacity);

                courseRepository.save(course);

                return true;

            }
        }catch (Exception e){
            return false;
        }
       return  false;
    }

    public boolean unregister(Authentication principal){
        try{
        Student student= (Student) principal.getPrincipal();
        if(student.getCourse()!=null){
        Course course=student.getCourse();
        int capacity=course.getCapacity()+1;
        course.setCapacity(capacity);
        courseRepository.save(course);

        student.setCourse(null);
        studentRepository.save(student);
        return  true;}
        }catch (Exception e){
            return false;
        }
        return false;

    }



    public Student getStudentById(int studentId){
        return studentRepository.getById(studentId);

    }

    public Profile profile( Authentication principal){
        Student student= (Student) principal.getPrincipal();
        Course course=student.getCourse();
        Profile profile=new Profile();
        if(course!=null){
            profile.setCourseName(course.getTitle());
        }

        profile.setId(student.getId());
        profile.setStudentName(student.getName());

        return profile;
    }

    private Student getById(int id){
        Student student=studentRepository.getById(id);
        return student;
    }



}
