package com.AccioJob.LibraryManagementSystem.Controller;

import com.AccioJob.LibraryManagementSystem.Entities.Student;
import com.AccioJob.LibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controller class for handling Student-related HTTP requests
@RestController                                     // Annotation to indicate that this class is a REST controller
@RequestMapping("/student")                         // Base mapping for all endpoints in this controller
public class StudentController {

    @Autowired                                      // Autowired annotation to inject an instance of StudentService
    private StudentService studentService;

    @PostMapping("/add")                            // Endpoint for adding a student via HTTP POST method
    public String addStudent(@RequestBody Student student){
        String result =studentService.addStudent(student);
        return result;
    }

    @GetMapping("/getTopperStudents")               // Endpoint to retrieve a list of students who are toppers in a particular course based on CGPA
    public List<Student> getStudents(@RequestParam("course") String course, @RequestParam("cgpa") double cgpa){
        List<Student> studentList = studentService.findStudent(course,cgpa);
        return studentList;
    }
}