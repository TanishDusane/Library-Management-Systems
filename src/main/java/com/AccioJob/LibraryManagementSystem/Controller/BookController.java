package com.AccioJob.LibraryManagementSystem.Controller;

import com.AccioJob.LibraryManagementSystem.Entities.Book;
import com.AccioJob.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controller class for handling Book-related HTTP requests
@RestController                                     // Annotation to indicate that this class is a REST controller
@RequestMapping("/book")                            // Base mapping for all endpoints in this controller
public class BookController {

    @Autowired                                      // Auto-wiring BookService to handle business logic
    private BookService bookService;

    @PostMapping("add")
    public ResponseEntity addbooks(@RequestBody Book book){     // Endpoint for adding a new book via HTTP POST request

        try{
            String result = bookService.addbooks(book);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/associateBooksAndAuthor")           // Endpoint for associating books and author via HTTP PUT request
    public String associateBooksAndAuthor(@RequestParam("bookId") int bookId, @RequestParam("authorId") int authorId) {
        String result = bookService.associateBooksAndAuthor(bookId,authorId);

        return result;
    }

    @GetMapping("/getBookByAuthor")                   // Endpoint for retrieving books by author via HTTP GET request
    public List<Book> getBookByAuthor(@RequestParam("authorId") Integer authorId){
        List<Book> ansList = bookService.findBookByAuthor(authorId);
        return ansList;
    }
}