package com.AccioJob.LibraryManagementSystem.Services;

import com.AccioJob.LibraryManagementSystem.Enums.CardStatus;
import com.AccioJob.LibraryManagementSystem.Entities.LibraryCard;
import com.AccioJob.LibraryManagementSystem.Entities.Student;
import com.AccioJob.LibraryManagementSystem.Repositories.CardRepository;
import com.AccioJob.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service                         // Indicates that this class is a service component
public class CardService {

    @Autowired                    // Injects an instance of CardRepository
    private CardRepository cardRepository;

    @Autowired                    // Injects an instance of StudentRepository
    private StudentRepository studentRepository;

    // Method to generate a library card
    public String generatecard(){

        // Create a new library card
        LibraryCard card = new LibraryCard();
        card.setNoOfBooksIssued(0);          // Set the number of books issued to 0
        card.setCardStatus(CardStatus.New);  // Set the card status to New

        // Set the expiration date to July 12, 1028
        LocalDate expireDate = LocalDate.of(1028, 7, 12);
        card.setValidity(expireDate);

        // Save the new library card to the database
        card = cardRepository.save(card);

        // Return a success message with the generated card's ID
        return "Card generated successfully with CardID: " + card.getCardNo();
    }

    // Method to associate a library card with a student
    public String associateCardsAndStudents(int cardId, int studentId){

        // Retrieve the library card and student entities from their respective repositories
        LibraryCard libraryCard = cardRepository.findById(cardId).get();
        Student student = studentRepository.findById(studentId).get();

        // Update the library card status to Issued and associate it with the student
        libraryCard.setCardStatus(CardStatus.Issued);
        libraryCard.setStudent(student);

        // Save the updated library card to the database
        cardRepository.save(libraryCard);

        // Return a success message indicating that cards and students are associated
        return "Cards and students successfully associated.";
    }
}