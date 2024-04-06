package com.AccioJob.LibraryManagementSystem.Controller;

import com.AccioJob.LibraryManagementSystem.Entities.Author;
import com.AccioJob.LibraryManagementSystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// Controller class for handling Author-related HTTP requests
@RestController                                     // Annotation to indicate that this class is a REST controller
@RequestMapping("/author")                          // Base mapping for all endpoints in this controller
public class AuthorController {

    @Autowired                                      // Auto-wiring AuthorService to handle business logic
    private AuthorService authorService;

    @PostMapping("add")                             // Endpoint for adding a new Author via HTTP POST request
    public String addAuthor(@RequestBody Author author){
        String result = authorService.addAuthor(author);    // Calling the service method to add the author
        return result;
    }

    @GetMapping("/getMaxBooks")                     // Defines a method to retrieve the author with the maximum number of books
    public Author getAuthor(){
        Author author = authorService.getAuthorWithMaxBooks();
        return author;
    }
}
