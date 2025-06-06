package com.mourat.udemy.demo.rest;

import com.mourat.udemy.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void initStudents(){

        // initialize the student list
        students = new ArrayList<>();

        // add 3 students to the student list
        students.add(new Student("Mourat", "Achoi"));
        students.add(new Student("Amet", "Amet oglou"));
        students.add(new Student("Kostas", "Vardakis"));

        System.out.println("Students initialization complete!");
    }

    @GetMapping("/students")
    public List<Student> getStudents(){

        // returns the list of students
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // check the student id to be valid
        if(studentId < 0 || studentId >= students.size())
            throw new StudentNotFoundException("Student not found - " + studentId);

        // find the student with the given id and return
        return students.get(studentId);
    }

}
