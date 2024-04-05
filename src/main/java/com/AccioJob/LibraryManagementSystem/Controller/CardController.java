package com.AccioJob.LibraryManagementSystem.Controller;

import com.AccioJob.LibraryManagementSystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controller class for handling Card-related HTTP requests
@RestController                                     // Annotation to indicate that this class is a REST controller
@RequestMapping("/card")                            // Base mapping for all endpoints in this controller
public class CardController {

    @Autowired                                      // Auto-wiring CardService to handle business logic
    private CardService cardService;

    @PostMapping("/generateCard")                   // Endpoint for generating a new card via HTTP POST request
    public ResponseEntity addCard(){
        String result = cardService.generatecard();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateCardsAndStudents")       // Endpoint for associating cards and students via HTTP PUT request
    public ResponseEntity associateCardsAndStudents(@RequestParam("cardId") int cardId,
                                                    @RequestParam("studentId") int studentId){

        String result = cardService.associateCardsAndStudents(cardId,studentId);

        return new ResponseEntity(result,HttpStatus.OK);
    }
}