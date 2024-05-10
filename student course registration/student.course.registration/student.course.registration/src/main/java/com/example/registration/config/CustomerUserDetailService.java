package com.example.registration.config;

import com.example.registration.student.entities.Student;
import com.example.registration.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Student student = studentRepository.findOneByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("user with eamil "+email+"is not present"));
        return student;
    }
}
