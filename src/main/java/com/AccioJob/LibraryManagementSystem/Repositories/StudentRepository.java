package com.AccioJob.LibraryManagementSystem.Repositories;

import com.AccioJob.LibraryManagementSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                       // Indicates that this interface is a Spring Data repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    // This interface extends JpaRepository for CRUD operations on Student entities with Integer primary key
}
