package com.AccioJob.LibraryManagementSystem.Services;

import com.AccioJob.LibraryManagementSystem.Entities.Author;
import com.AccioJob.LibraryManagementSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service                         // Indicates that this class is a service component
public class AuthorService {

    @Autowired                    // Injects an instance of AuthorRepository
    private AuthorRepository authorRepository;

    // Method to add an author
    public String addAuthor(Author author){

        author.setNoOfBooks(0);   // Setting the initial number of books to 0
        authorRepository.save(author);  // Saving the author to the database

        return "Author added successfully.";
    }
}