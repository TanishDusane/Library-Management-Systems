package com.AccioJob.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                         // Entity annotation indicates this class is a JPA entity
@Table(name = "StudentInfo")    // Table annotation specifies the name of the table in the database
@Getter
@Setter
@NoArgsConstructor              //generates a constructor with no arguments class.
@AllArgsConstructor             //generates a constructor that initializes all fields of the class.
public class Student {

    // Primary key annotation with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // Specifies auto-increment strategy for the primary key
    private int rollNo;

    @Column(nullable = false)       // Specifies that the noOfPages column cannot have null values
    private String studentName;

    @Column(unique = true)          // Specifies that the title column should have unique values
    private String emailId;

    private double CGPA;
    private int age;
    private String course;
}
