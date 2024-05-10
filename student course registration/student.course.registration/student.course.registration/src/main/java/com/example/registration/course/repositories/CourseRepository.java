package com.example.registration.course.repositories;

import com.example.registration.course.entities.Course;
import com.example.registration.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query(value = "Select capacity from Course where course_code=:courseCode",nativeQuery = true)
    public int getCapacity(@Param("courseCode") int courseCode);

    @Query(value = "Select schedule from Course where course_code=:courseCode",nativeQuery = true)
    public String getSchedule(@Param("courseCode") int courseCode);



    @Query("SELECT a from  Course a WHERE  a.title LIKE %?1%")
    public List<Course> getCourseByKeyword(String keyword);
}
