package com.equiptal.BookShelf;

import java.util.List;

public class BookDisplayer {
    public void displayBook(Book book){
        System.out.println(book != null ? book : "Cannot display such information");
    }

    public void displayAllBooks(List<Book> books){
        for(Book book : books){
            System.out.println(book);
            System.out.println();
        }
    }
    
}
