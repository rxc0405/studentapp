package com.example.studentapp;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public String greetStudent(@PathVariable int id) {

        Optional<Student> student = repo.findById(id);

        if(student.isPresent())
        {
            return "Hello " + student.get().getName();
        }
        else{
            return "Student not found";
        }
        
    }

    @GetMapping("/name/{name}")
    public String RevealID(@PathVariable String name) {

        List<Student> student = repo.findByName(name);

        if(student.isEmpty())
        {
            return name + " is not in database";
        }

        return student.get(0).getName() + "'s ID is: " + student.get(0).getId();

       
        
    }
}
