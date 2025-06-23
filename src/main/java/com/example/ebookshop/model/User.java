package com.example.ebookshop.model; // This defines the "folder path" as a Java package
import jakarta.validation.constraints.*;

import jakarta.persistence.*; // This allows us to use annotations to map to the database

@Entity  // Tells Spring Boot: "This class maps to a table in the database"
@Table(name = "users") // The table in your database is called "books"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int id;

    @NotBlank
    @Column(name = "UserName")
    private String username;

    @NotBlank
    @Column(name = "UserPassword")
    private String password;

    // Java needs getters and setters to access private fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}