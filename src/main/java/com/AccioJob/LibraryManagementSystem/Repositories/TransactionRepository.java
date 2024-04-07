package com.AccioJob.LibraryManagementSystem.Repositories;

import com.AccioJob.LibraryManagementSystem.Entities.Book;
import com.AccioJob.LibraryManagementSystem.Entities.LibraryCard;
import com.AccioJob.LibraryManagementSystem.Entities.Transaction;
import com.AccioJob.LibraryManagementSystem.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                       // Indicates that this interface is a Spring Data repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    // This interface extends JpaRepository for CRUD operations on Transaction entities with String primary key

     Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
