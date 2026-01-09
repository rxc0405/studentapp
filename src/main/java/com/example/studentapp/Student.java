package com.example.studentapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id") //optional dont need
    private Integer id;

    @Column(name = "name") //optional again, good for practice
    private String name;

    public Student() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
