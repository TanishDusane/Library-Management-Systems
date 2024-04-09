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
        return "The student data has been successfully stored in the database.";
    }

    // Method to find students based on a given course and a minimum CGPA.
    public List<Student> findStudent(String course, double cgpa) {
        // Retrieve students from the repository who are enrolled in the specified course and have a CGPA greater than the provided value.
        List<Student> students = studentRepository.findStudentByCourseAndCGPAGreaterThan(course, cgpa);
        // Return the list of matching students.
        return students;
    }
}
