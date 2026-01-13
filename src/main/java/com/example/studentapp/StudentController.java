package com.example.studentapp;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> greetStudent(@PathVariable int id) {

        Optional<Student> student = repo.findById(id);

        if(student.isPresent())
        {
            return ResponseEntity.ok(student.get()); //returns JSON data from the database automatically
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){

        return ResponseEntity.ok(repo.findAll());
        
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        //TODO: process POST request
        
        if(repo.existsById(student.getId()))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student with that ID already exists");
        }
        
        repo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added Succesfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id)
    {
        if(repo.existsById(id))
        {
            repo.deleteById(id);
            return ResponseEntity.ok("Student deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with that id does not exist");
        }
    }
    
    
}
