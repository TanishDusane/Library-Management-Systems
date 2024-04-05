package com.AccioJob.LibraryManagementSystem.Services;

import com.AccioJob.LibraryManagementSystem.Entities.Book;
import com.AccioJob.LibraryManagementSystem.Entities.LibraryCard;
import com.AccioJob.LibraryManagementSystem.Entities.Transaction;
import com.AccioJob.LibraryManagementSystem.Enums.TransactionStatus;
import com.AccioJob.LibraryManagementSystem.Repositories.BookRepository;
import com.AccioJob.LibraryManagementSystem.Repositories.CardRepository;
import com.AccioJob.LibraryManagementSystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service                                        // Indicates that this class is a service component
public class TransactionService {

    @Autowired                                  // Injects an instance of BookRepository
    private BookRepository bookRepository;

    @Autowired                                  // Injects an instance of CardRepository
    private CardRepository cardRepository;

    @Autowired                                  // Injects an instance of TransactionRepository
    private TransactionRepository transactionRepository;

    public static Integer MAX_NO_OF_ISSUED = 3;         // Maximum number of books that can be issued to a card

    public String issueBook(Integer bookId, Integer cardId) throws Exception {
        // Retrieve the book by its ID
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new Exception("Invalid Book ID provided.");               // Throw exception if the book ID is invalid
        }
        Book book = bookOptional.get();

        // Retrieve the library card by its ID
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if (optionalLibraryCard.isEmpty()) {
            throw new Exception("Invalid Card ID provided.");               // Throw exception if the card ID is invalid
        }
        LibraryCard card = optionalLibraryCard.get();

        // Create a new transaction for the issued book
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        transaction.setTransactionStatus(TransactionStatus.Pending);

        // Check if the book is already issued to another card
        if (book.isIssued()) {
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transactionRepository.save(transaction);
            return "The book has already been issued to another card." + card.getCardNo();
        }

        // Check if the maximum number of books issued to the card is exceeded
        if (card.getNoOfBooksIssued() > MAX_NO_OF_ISSUED) {
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transactionRepository.save(transaction);
            return "Maximum card limit exceeded.";
        }

        // Check if the library card has expired
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(card.getValidity())) {
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transactionRepository.save(transaction);
            return "The card has expired.";
        }

        // Update book and card information and save the transaction
        book.setIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued() + 1);

        cardRepository.save(card);
        bookRepository.save(book);
        transactionRepository.save(transaction);

        // Return success message with transaction ID
        return "Congratulations! Your transaction has been processed successfully. Here's your transaction ID:" + transaction.getTransactionId();
    }
}
