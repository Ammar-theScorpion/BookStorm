package com.equiptal.BookShelf;

import java.io.Serializable;

public class Book implements Serializable {

    public Book() {
    }

    public Book(String isbn, String title, String auther, double price) {
        this.ISBN = isbn;
        this.Title = title;
        this.Auther = auther;
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

    public String getAuther() {
        return this.Auther;
    }

    public void setAuther(String Auther) {
        this.Auther = Auther;
    }

    public double getPrice() {
        return this.Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "ISBN--> " + ISBN + ", Title--> " + Title + ", Auther--> " + Auther + ", Price--> " + Price;
    }

    private String ISBN;
    private String Title;
    private String Auther;
    private double Price;
    private static final long serialVersionUID = 1L;
}