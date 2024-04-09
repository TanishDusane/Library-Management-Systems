package com.AccioJob.LibraryManagementSystem.Services;

import com.AccioJob.LibraryManagementSystem.Controller.AuthorController;
import com.AccioJob.LibraryManagementSystem.Entities.Author;
import com.AccioJob.LibraryManagementSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // Method to retrieve the author with the maximum number of books in the author repository.
    public Author getAuthorWithMaxBooks() {
        // Retrieve all authors from the repository.
        List<Author> authorList = authorRepository.findAll();

        // Initialize variables to track the author with the maximum number of books and the maximum number of books found so far.
        Author authorWithMaxBooks = null;
        int maxBooks = 0;

        // Iterate through the list of authors to find the one with the maximum number of books.
        for (Author author : authorList) {
            // Check if the current author has more books than the maximum number found so far.
            if (author.getNoOfBooks() > maxBooks) {
                // Update the maximum number of books and the author with the maximum number of books.
                maxBooks = author.getNoOfBooks();
                authorWithMaxBooks = author;
            }
        }
        // Return the author with the maximum number of books.
        return authorWithMaxBooks;
    }
}