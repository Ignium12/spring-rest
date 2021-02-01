package com.kruehl.spring.rest;

import com.kruehl.spring.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // declare list
    private List<Student> students;

    // define @PostConstruct to load the student data.... only once
    @PostConstruct
    public void loadData(){
        // initialize list
        students = new ArrayList<>();

        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Mario", "Rossi"));
        students.add(new Student("Mary", "Smith"));
    }

    // define endpoint for /students - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return students;
    }

    // define endpoint for "/students/{studentId} - return student at index
    @GetMapping("students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // just index into the list...keep it simple for now

        return students.get(studentId);
    }
}
