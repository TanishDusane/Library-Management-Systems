package com.AccioJob.LibraryManagementSystem.Controller;

import com.AccioJob.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Controller class for handling Transaction-related HTTP requests
@RestController                                     // Annotation to indicate that this class is a REST controller
@RequestMapping("/transaction")                      // Base mapping for all endpoints in this controller
public class TransactionController {

    @Autowired                                      // Auto-wiring TransactionService to handle business logic
    private TransactionService transactionService;

    @PutMapping("/issueBook")                        // Endpoint for issuing a book via HTTP PUT request
    public String issueBook(@RequestParam("cardId") Integer cardId, @RequestParam("bookId") Integer bookId) {

        String result;

        try {
            result = transactionService.issueBook(cardId,bookId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam("cardId") Integer cardId, @RequestParam("bookId") Integer bookId) {
        String result = transactionService.returnBook(bookId,cardId);
        return result;
    }
}
