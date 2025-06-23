package com.example.ebookshop.model; 
import jakarta.validation.constraints.*;

import jakarta.persistence.*; 

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Min(1)
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @DecimalMin("0.0")
    private double price;

    @Min(0)
    private int qty;

    @NotBlank
    private String imageUrl;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
