package com.equiptal.BookShelf;

import java.io.Serializable;

public class Book implements Serializable {

    public Book() {
    }

    public Book(String isbn, String title, String author, double price) {
        this.ISBN = isbn;
        this.Title = title;
        this.Author = author;
        this.Price = price;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return this.Author;
    }

    public void setAuthor(String Auther) {
        this.Author = Auther;
    }

    public double getPrice() {
        return this.Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "ISBN--> " + ISBN + ", Title--> " + Title + ", Auther--> " + Author + ", Price--> " + Price;
    }

    private String ISBN;
    private String Title;
    private String Author;
    private double Price;
    private static final long serialVersionUID = 1L;
}