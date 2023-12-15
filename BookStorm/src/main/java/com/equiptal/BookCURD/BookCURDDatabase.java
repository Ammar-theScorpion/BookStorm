package com.equiptal.BookCURD;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.equiptal.BookShelf.Book;

public class BookCURDDatabase implements BookCURDInterface{

    public BookCURDDatabase(){
        String path = "jdbc:oracle:thin:@//localhost:1521/orcl";
        
        String name ="system";
        String pass="!!hkhulhvuhwd";

        jdbi = Jdbi.create(path, name, pass);
        try{
            jdbi.useHandle(handle ->handle.execute("BEGIN EXECUTE IMMEDIATE 'CREATE TABLE Book (isbn VARCHAR2(10) PRIMARY KEY, title VARCHAR2(30), author VARCHAR2(30), price NUMBER)'; EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF; END;"));
        }catch(Exception e){
            System.err.println(e);
        }
    } 

    @Override
    public String store(Book book) {
        try {
            jdbi.useHandle(handle -> {
                handle.createUpdate("INSERT INTO Book (isbn, title, author, price) VALUES (?, ?, ?, ?)")
                        .bind(0, book.getISBN())
                        .bind(1, book.getTitle())
                        .bind(2, book.getAuthor())
                        .bind(3, book.getPrice())
                        .execute();
            });
            return book.getISBN() + " Added Successfully";
        } catch (Exception e) {
            return "Error Storing a book " + e.toString();
        }
    }

    @Override
    public Book searchByISBN(String isbn) {
        try{
            return jdbi.withHandle(handle -> {
                    return handle.createQuery("SELECT * from Book where isbn = ?")
                          .bind(0, isbn)
                          .mapToBean(Book.class)
                          .findFirst()
                          .orElse(null);
            });
            
        }catch(Exception e){
            System.err.println(e);
        } 
        return null;
    }

    @Override
    public Book deleteByISBN(String isbn) {
        Book book = searchByISBN(isbn);
        if(book != null)
            jdbi.useHandle(handel -> {
               int rows = handel.createUpdate("DELETE from Book Where isbn = ?")
                        .bind(0, isbn)
                        .execute();
        });
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbi.withHandle(handel-> handel.createQuery("SELECT * from Book")
                                    .mapToBean(Book.class).list());
        
    }

    @Override
    public boolean isEmpty() {
        return getAllBooks().size() == 0;
    }

    private Jdbi jdbi;
}
