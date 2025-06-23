package com.example.ebookshop.model; 
import jakarta.validation.constraints.*;

import jakarta.persistence.*;

@Entity 
@Table(name = "users") 
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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
