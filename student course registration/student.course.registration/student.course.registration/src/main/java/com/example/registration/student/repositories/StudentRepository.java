package com.example.registration.student.repositories;

import com.example.registration.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    public Optional<Student> findOneByEmailIgnoreCase(String email);

    @Query(value = "select course_id from student where id=:id",nativeQuery = true)
    public int getCourseId(@Param("id") int id);
}
