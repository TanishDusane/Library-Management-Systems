package com.AccioJob.LibraryManagementSystem.Repositories;

import com.AccioJob.LibraryManagementSystem.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // Annotation indicating that this interface is a repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
                // This interface extends JpaRepository for CRUD operations on Author entities with Integer primary key
}
