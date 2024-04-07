package com.AccioJob.LibraryManagementSystem.Services;

import com.AccioJob.LibraryManagementSystem.Entities.Author;
import com.AccioJob.LibraryManagementSystem.Entities.Book;
import com.AccioJob.LibraryManagementSystem.Exceptions.InvalidPageCount;
import com.AccioJob.LibraryManagementSystem.Repositories.AuthorRepository;
import com.AccioJob.LibraryManagementSystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service                         // Indicates that this class is a service component
public class BookService {

    @Autowired                    // Injects an instance of BookRepository
    private BookRepository bookRepository;

    @Autowired                    // Injects an instance of AuthorRepository
    private AuthorRepository authorRepository;

    // Method to add a book
    public String addbooks(Book book) throws Exception{
        // Check if the number of pages is positive
        if(book.getNoOfPages() <= 0){
            throw new InvalidPageCount("Invalid page count: The number of pages cannot be negative.");
        }
        book.setIssued(Boolean.FALSE);
        bookRepository.save(book);  // Saving the book to the database
        return "Book added successfully.";
    }

    // Method to associate a book with an author
    public String associateBooksAndAuthor(int bookId, int authorId){
        // Retrieve the book and author entities from their respective repositories
        Book book = bookRepository.findById(bookId).get();
        Author author = authorRepository.findById(authorId).get();

        // Set the author for the book
        book.setAuthor(author);
        // Increment the number of books written by the author
        author.setNoOfBooks(author.getNoOfBooks()+1);

        // Save the updated book and author entities to the database
        bookRepository.save(book);
        authorRepository.save(author);

        return "Book and Author have been associated";
    }

    // Method to find books by author
    public List<Book> findBookByAuthor(Integer authorId){

        // Retrieve all books from the database
        List<Book> allBooks = bookRepository.findAll();
        // Create a list to store books by the specified author
        List<Book> result = new ArrayList<>();

        // Iterate through all books
        for(Book book : allBooks){
            // Check if the book's author matches the specified authorId
            if (authorId != null && authorId.equals(book.getAuthor().getAuthorId())) {
                result.add(book);  // Add the book to the result list
            }
        }

        return result;  // Return the list of books by the specified author
    }
}