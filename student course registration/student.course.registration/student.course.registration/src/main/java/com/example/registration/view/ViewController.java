package com.example.registration.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/studentHome")
    public String base() {
        return "Student/studentIndex";
    }

    @RequestMapping("/viewCourse")
    public String viewCourse() {
        return "Student/viewCourse";
    }

    @RequestMapping("/studentRegister")
    public String studentRegister() {
        return "student";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "Student/profile";
    }

    @RequestMapping("/signin")
    public String customLogin(Model model)
    {
        model.addAttribute("title", "Login Page");
        return "signin";
    }

    @RequestMapping("/register/{courseId}")
    public String register(@PathVariable int courseId) {
        return "Student/register";
    }
}
