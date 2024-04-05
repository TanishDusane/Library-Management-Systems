package com.AccioJob.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                              // Entity annotation indicates this class is a JPA entity
@Table                               // Table annotation specifies the name of the table in the database
@Getter
@Setter
@NoArgsConstructor                  //generates a constructor with no arguments class.
@AllArgsConstructor                 //generates a constructor that initializes all fields of the class.
public class Author {

    // Primary key annotation with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // Specifies auto-increment strategy for the primary key
    private int authorId;

    private String name;
    private int age;
    private String emailId;
    private double rating;

    @Column(columnDefinition = "INT DEFAULT 0")         // Column definition with default value 0 for "noOfBooks"
    private int noOfBooks;
}
