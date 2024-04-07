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
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service                                        // Indicates that this class is a service component
public class TransactionService {

    @Autowired                                  // Injects an instance of BookRepository
    private BookRepository bookRepository;

    @Autowired                                  // Injects an instance of CardRepository
    private CardRepository cardRepository;

    @Autowired                                  // Injects an instance of TransactionRepository
    private TransactionRepository transactionRepository;

    public static Integer MAX_NO_OF_ISSUED = 3;         // Maximum number of books that can be issued to a card

    public static Integer FINE_PER_DAY = 5;

    public String issueBook(Integer cardId, Integer bookId) throws Exception {

        // Check if the book ID is null
        if (bookId == null) {
            return "Book ID is missing. Please provide the book ID.";
        }

        // Check if the card ID is null
        if (cardId == null) {
            return "Card ID is missing. Please provide the card ID.";
        }

        // Retrieve the book by its ID
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isEmpty() || bookId < 0) {
            return "Invalid Book ID provided.";               // Throw exception if the book ID is invalid
        }
        Book book = bookOptional.get();

        // Retrieve the library card by its ID
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if (optionalLibraryCard.isEmpty() || cardId < 0 || cardId == null) {
            return "Invalid Card ID provided.";               // Throw exception if the card ID is invalid
        }
        LibraryCard card = optionalLibraryCard.get();

        // Create a new transaction for the issued book
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.Issued);

        // Check if the book is already issued to another card
        if (book.isIssued()) {
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transactionRepository.save(transaction);
            return "The book has already been issued to another card." + card.getCardNo();
        }

        // Check if the maximum number of books issued to the card is exceeded
        if (card.getNoOfBooksIssued() >= MAX_NO_OF_ISSUED) {
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transactionRepository.save(transaction);
            return "Maximum card limit exceeded.";
        }

        // Check if the library card has expired
        Long timeInMsOfCardValidity = card.getValidity().getTime();
        Long currentTime = System.currentTimeMillis();

        if (currentTime > timeInMsOfCardValidity) {
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

    public String returnBook(Integer bookId, Integer cardId) {
        // Retrieve the book by its ID
        Book book = bookRepository.findById(bookId).get();

        // Retrieve the library card by its ID
        LibraryCard card = cardRepository.findById(cardId).get();

        // Find the transaction for the issued book and card
        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book, card, TransactionStatus.Issued);

        // Calculate the duration for which the book was issued
        Long timeDiffInMs = System.currentTimeMillis() - transaction.getIssuedDate().getTime();
        Long days = TimeUnit.DAYS.convert(timeDiffInMs, TimeUnit.MILLISECONDS);

        // Calculate the fine amount if the book is returned after the due date
        Integer fineAmount = 0;
        if (days > 15) {
            fineAmount = Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        // Update transaction details
        transaction.setFineAmount(fineAmount);
        transaction.setTransactionStatus(TransactionStatus.Completed);
        transaction.setReturnDate(new Date());

        // Update book status
        book.setIssued(false);

        // Update library card details
        card.setNoOfBooksIssued(card.getNoOfBooksIssued() - 1);

        // Save changes to database
        transactionRepository.save(transaction);
        bookRepository.save(book);
        cardRepository.save(card);

        // Return success message
        return "The Book has been returned Successfully";
    }
}