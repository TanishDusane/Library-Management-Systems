package com.AccioJob.LibraryManagementSystem.Entities;

import com.AccioJob.LibraryManagementSystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;

@Entity                             // Entity annotation indicates this class is a JPA entity
@Table(name = "transaction")        // Table annotation specifies the name of the table in the database
@Getter
@Setter
@NoArgsConstructor                  //generates a constructor with no arguments class.
@AllArgsConstructor                 //generates a constructor that initializes all fields of the class.
public class Transaction {

    // Primary key annotation with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)         // Specifies UUID generation strategy for the primary key
    private String transactionId;

    @Enumerated(value = EnumType.STRING)        // Specifies the type of enumeration used for the genre column
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date issuedDate;

    private Date returnDate;
    private Integer fineAmount;


    @JoinColumn                     // Specifies the foreign key column for the association with the Book table
    @ManyToOne
    private Book book;

    @JoinColumn                     // Specifies the foreign key column for the association with the LibraryCard table
    @ManyToOne
    private LibraryCard card;
}
