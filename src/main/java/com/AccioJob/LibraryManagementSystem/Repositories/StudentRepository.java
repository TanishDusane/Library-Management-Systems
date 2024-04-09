package com.AccioJob.LibraryManagementSystem.Repositories;

import com.AccioJob.LibraryManagementSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Indicates that this interface is a Spring Data repository
// This interface extends JpaRepository for CRUD operations on Student entities with Integer primary key
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    // Method to find students by course and CGPA greater than a certain value
    List<Student> findStudentByCourseAndCGPAGreaterThan(String course, double cgpa);

    // Method to find student by email ID
    Student findStudentByEmailId(String emailId);


}
