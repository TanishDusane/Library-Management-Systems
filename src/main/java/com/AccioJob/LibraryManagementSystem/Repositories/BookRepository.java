package com.AccioJob.LibraryManagementSystem.Repositories;

import com.AccioJob.LibraryManagementSystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // Annotation indicating that this interface is a repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    // This interface extends JpaRepository for CRUD operations on Book entities with Integer primary key
}

