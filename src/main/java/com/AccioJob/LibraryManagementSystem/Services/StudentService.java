package com.AccioJob.LibraryManagementSystem.Services;

import com.AccioJob.LibraryManagementSystem.Entities.Student;
import com.AccioJob.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service                                                     // Service annotation indicates that this class is a service component in Spring
public class StudentService {

    @Autowired                                               // Autowired annotation to inject an instance of StudentRepository
    private StudentRepository studentRepository;

    public String addStudent(Student student){              // Method to add a student to the database
        studentRepository.save(student);
        return "The student's data has been successfully stored in the database.";
    }

    public List<Student> findStudent(String branch, double cgpa) {
        List<Student> students = studentRepository.findStudentByBranchAndCgpaGreaterThan(branch,cgpa);
        return students;
    }
}
