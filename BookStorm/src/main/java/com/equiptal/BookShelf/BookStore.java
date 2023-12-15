package com.equiptal.BookShelf;

import java.util.List;

import com.equiptal.BookCURD.BookCURDInterface;

import jakarta.inject.Inject;

/*
    Main class; has access to all operations 
*/
public class BookStore {
    @Inject

    public BookStore(BookDisplayer bookDisplayer, BookCURDInterface bookCURD) {
        this.bookDisplayer = bookDisplayer;
        this.bookCURD = bookCURD;
    }

    public void store(Book book) {
        bookCURD.store(book);
    }

    public List<Book> displayAllBooks() {
        return bookCURD.getAllBooks();
    }

    public void displayBook(Book book) {
        bookDisplayer.displayBook(book);
    }

    public Book searchByISBN(String isbn) {
        Book book = bookCURD.searchByISBN(isbn);
        return book;

    }

    public Book delete(String isbn) {
        Book book = bookCURD.deleteByISBN(isbn);
        return book;

    }

    public boolean canDoOperations() {
        return bookCURD.isEmpty();
    }

    private final BookDisplayer bookDisplayer;
    private final BookCURDInterface bookCURD;
}
