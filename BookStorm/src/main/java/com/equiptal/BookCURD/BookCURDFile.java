package com.equiptal.BookCURD;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.equiptal.BookShelf.Book;

public class BookCURDFile implements BookCURDInterface {

    public BookCURDFile(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(this.filePath);
            if (!file.exists())
                file.createNewFile();
            initFile();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public String store(Book book) {
        try {

            oos.writeObject(book);
            return "Book stored successfully.";
        } catch (IOException e) {
            return "Error storing book: " + e.getMessage();
        }
    }

    @Override
    public Book searchByISBN(String isbn) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Book book = (Book) ois.readObject();
                    if (book.getISBN().equals(isbn)) {
                        return book;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public Book deleteByISBN(String isbn) {
        Book bookToDelete = null;
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream objIs = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                Book book = (Book) objIs.readObject();
                if (!book.getISBN().equals(isbn)) {
                    books.add(book);
                } else {
                    bookToDelete = book;
                }
            }
        } catch (Exception e) {
        }
        if (bookToDelete != null) {
            try {
                new FileOutputStream(filePath).close();
                initFile();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Book book : books) {
                store(book);
            }
        }

        return bookToDelete;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream objIs = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                Book book = (Book) objIs.readObject();
                books.add(book);
            }
        } catch (EOFException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean isEmpty() {
        return getAllBooks().size() == 0;
    }

    private void initFile() throws Exception {

        if (!isEmpty()) {
            List<Book> books = new ArrayList<>();
            try (ObjectInputStream objIs = new ObjectInputStream(new FileInputStream(filePath))) {
                while (true) {
                    Book book = (Book) objIs.readObject();
                    books.add(book);
                }
            } catch (Exception e) {
            }
            try {
                new FileOutputStream(filePath).close();
                fos = new FileOutputStream(filePath, true);
                oos = new ObjectOutputStream(fos);

            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Book book : books) {
                store(book);
            }
        } else {
            fos = new FileOutputStream(filePath, true);
            oos = new ObjectOutputStream(fos);
        }
    }

    @Override
    protected void finalize() throws Exception {
        fos.close();
        oos.close();
    }

    private String filePath;
    private ObjectOutputStream oos;
    private FileOutputStream fos;
}
