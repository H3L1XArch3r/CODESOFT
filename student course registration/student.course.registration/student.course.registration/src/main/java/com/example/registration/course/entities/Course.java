package com.example.registration.course.entities;

import com.example.registration.student.entities.Student;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseCode;

    private String title;

    private String description;

    private int capacity;




    private String schedule;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)

    private Set<Student> student;

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
