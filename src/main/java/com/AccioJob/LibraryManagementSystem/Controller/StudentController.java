package com.AccioJob.LibraryManagementSystem.Controller;

import com.AccioJob.LibraryManagementSystem.Entities.Student;
import com.AccioJob.LibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller class for handling Student-related HTTP requests
@RestController                                     // Annotation to indicate that this class is a REST controller
@RequestMapping("/student")                         // Base mapping for all endpoints in this controller
public class StudentController {

    @Autowired                                                 // Autowired annotation to inject an instance of StudentService
    private StudentService studentService;

    @PostMapping("/add")                                      // Endpoint for adding a student via HTTP POST method
    public String addStudent(@RequestBody Student student){

        String result =studentService.addStudent(student);

        return result;
    }
}