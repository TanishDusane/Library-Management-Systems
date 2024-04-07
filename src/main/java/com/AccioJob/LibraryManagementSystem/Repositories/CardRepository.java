package com.AccioJob.LibraryManagementSystem.Repositories;

import com.AccioJob.LibraryManagementSystem.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // Annotation indicating that this interface is a repository
public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
    // This interface extends JpaRepository for CRUD operations on LibraryCard entities with Integer primary key
}

