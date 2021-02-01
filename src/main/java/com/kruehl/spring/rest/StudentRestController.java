package com.kruehl.spring.rest;

import com.kruehl.spring.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // check the studentId against list size

        if((studentId >= students.size()) || (studentId <0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }


        return students.get(studentId);
    }

    // Add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){

        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
