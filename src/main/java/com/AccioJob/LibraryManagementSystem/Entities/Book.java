package com.AccioJob.LibraryManagementSystem.Entities;

import com.AccioJob.LibraryManagementSystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                             // Entity annotation indicates this class is a JPA entity
@Table                             // Table annotation specifies the name of the table in the database
@Getter
@Setter
@NoArgsConstructor                  //generates a constructor with no arguments class.
@AllArgsConstructor                 //generates a constructor that initializes all fields of the class.
public class Book {

    // Primary key annotation with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // Specifies auto-increment strategy for the primary key
    private int bookId;

    @Column(unique = true)          // Specifies that the title column should have unique values
    private String title;

    @Column(nullable = false)       // Specifies that the noOfPages column cannot have null values
    private int noOfPages;

    @Enumerated(value = EnumType.STRING)        // Specifies the type of enumeration used for the genre column
    private Genre genre;

    private boolean isIssued;
    private int price;

    @JoinColumn                     // Specifies the foreign key column for the association with the Author table
    @ManyToOne
    private Author author;

}
