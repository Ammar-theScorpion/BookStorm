package com.equiptal.BookUIX;

import java.util.List;

import com.equiptal.BookShelf.Book;
import com.equiptal.BookShelf.BookStore;

import jakarta.inject.Inject;

public class BookUI {

    @Inject
    public BookUI(BookStore bookStore) {
        this.bookStore = bookStore;

    }

    public void processBookInput(String isbn, String title, String author, double price) {
        bookStore.store(new Book(isbn, title, author, price));
    }

    public Book processSearch(String isbn) {
        if (canDoOperations("search")) {
            return bookStore.searchByISBN( isbn);
        }
        return null;

    }

    public List<Book> processDisplayAll() {
        return bookStore.displayAllBooks();
    }

    public Book processDelete(String isbn) {
        if (canDoOperations("delete")) {
            return bookStore.delete(isbn);
        }
        return null;
    }


    private boolean canDoOperations(String operation) {
        if (bookStore.canDoOperations()) {
            System.out.println("No data to " + operation + " from");
            return false;
        }
        return true;
    }

    private final BookStore bookStore;

}
