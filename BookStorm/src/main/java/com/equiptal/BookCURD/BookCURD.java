package com.equiptal.BookCURD;

import java.util.ArrayList;
import java.util.List;

import com.equiptal.BookShelf.Book;

public class BookCURD implements BookCURDInterface {

    public BookCURD() {
        books = new ArrayList<>();
    }

    @Override
    public String store(Book book) {
        try {
            books.add(book);
            return book.getISBN() + " Added Successfully";
        } catch (Exception e) {
            return "Error Storing a book " + e.toString();
        }
    }

    @Override
    public Book searchByISBN(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book deleteByISBN(String isbn) {
        Book book = searchByISBN(isbn);
        if (book != null) {
            books.remove(book);
            return book;
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public boolean isEmpty() {
        return books.size() == 0;
    }

    private ArrayList<Book> books;

}
