package com.AccioJob.LibraryManagementSystem.Entities;

import com.AccioJob.LibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Date;

@Entity                             // Entity annotation indicates this class is a JPA entity
@Table(name = "CardInfo")           // Table annotation specifies the name of the table in the database
@Getter
@Setter
@NoArgsConstructor                  //generates a constructor with no arguments class.
@AllArgsConstructor                 //generates a constructor that initializes all fields of the class.
public class LibraryCard {

    // Primary key annotation with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Specifies auto-increment strategy for the primary key
    private int cardNo;

    @Enumerated(value = EnumType.STRING)        // Specifies the type of enumeration used for the genre column
    private CardStatus cardStatus;

    private int noOfBooksIssued;
    private Date validity;

    @JoinColumn                     // Specifies the foreign key column for the association with the Student table
    @OneToOne
    private Student student;
}
