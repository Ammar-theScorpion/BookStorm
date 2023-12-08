package com.equiptal.BookShelf;

import com.equiptal.BookCURD.BookCURDInterface;

/*
    Main class; has access to all operations 
*/
public class BookStore {

    public BookStore(BookDisplayer bookDisplayer, BookCURDInterface bookCURD) {
        this.bookDisplayer = bookDisplayer;
        this.bookCURD = bookCURD;
    }

    public void store(Book book) {
        bookCURD.store(book);
    }

    public void displayAllBooks() {
        bookDisplayer.displayAllBooks(bookCURD.getAllBooks());
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
