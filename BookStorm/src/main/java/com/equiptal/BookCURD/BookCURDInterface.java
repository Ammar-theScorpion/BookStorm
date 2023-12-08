package com.equiptal.BookCURD;

import java.util.List;

import com.equiptal.BookShelf.Book;

public interface BookCURDInterface {
    
    String store(Book book);
    Book searchByISBN(String isbn);
    Book deleteByISBN(String isbn);
    List<Book> getAllBooks();
    boolean isEmpty();
}
