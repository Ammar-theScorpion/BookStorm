package com.equiptal.BookUIX;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.equiptal.BookShelf.Book;
import com.equiptal.BookShelf.BookStore;

public class BookUI {

    public BookUI(BookStore bookStore) {
        this.bookStore = bookStore;
        this.scanner = new Scanner(System.in);

    }

    public void run() {

        Integer choice = 0;
        String input;
        do {
            System.out.println(
                    "1. Add Book\n" +
                            "2. Search by ISBN\n" +
                            "3. Display All Books\n" +
                            "4. Delete by ISBN\n" +
                            "5. Exit");

            input = scanner.nextLine();

            if (validChoice(input)) {
                choice = Integer.parseInt(input);
                if (choice == 5)
                    break;

                processInput(choice);

            } else {
                System.out.println("\n Enter a valid Input");
            }

        } while (true);

    }

    private boolean validChoice(String choice) {

        if (choice != null) {
            try {
                Integer isInteger = Integer.parseInt(choice);
                if (isInteger >= 1 && isInteger <= 5) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private void processInput(Integer choice) {

        switch (choice) {
            case 1:
                processBookInput();
                break;
            case 2:
                processSearch();
                break;
            case 3:
                processDisplayAll();
                break;
            case 4:
                processDelete();
                break;

        }
    }

    private void processBookInput() {
        String isbn = validISBN();
        System.out.println("Book's Title");
        String title = validString("title");
        System.out.println("Book's Auther");
        String auther = validString("auther");
        double price = validPrice();

        bookStore.store(new Book(isbn, title, auther, price));

    }

    private void processSearch() {
        if (canDoOperations("search")) {
            String isbn = validISBN();
            bookStore.displayBook(bookStore.searchByISBN(isbn));
        }
    }

    private void processDisplayAll() {
        bookStore.displayAllBooks();
    }

    private void processDelete() {
        if (canDoOperations("delete")) {
            String isbn = validISBN();
            bookStore.displayBook(bookStore.delete(isbn));
        }
    }

    private String validISBN() {
        String isbn;
        do {
            System.out.println("Enter 10-digit ISBN Code:");
            isbn = scanner.nextLine();
        } while (!isValidISBN10(isbn));
        return isbn;
    }

    private boolean isValidISBN10(String isbn10) {
        Matcher matcher = ISBN_10_REGEX.matcher(isbn10);
        return matcher.matches();
    }

    private double validPrice() {
        Double price = null;
        while (price == null) {

            System.out.println("Book's Price");

            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.err.println("Error: Price must be larger than 0.");
                    price = null;
                }
            } catch (NumberFormatException e) {
                System.err.println("Enter a valid price");
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        return price;
    }

    private String validString(String on) {
        String title = scanner.nextLine();
        while (title.trim().isEmpty()) {
            System.out.println("Title cannot be empty. Please enter a valid " + on);
            title = scanner.nextLine();
        }
        return title;
    }

    private boolean canDoOperations(String operation) {
        if (bookStore.canDoOperations()) {
            System.out.println("No data to " + operation + " from");
            return false;
        }
        return true;
    }

    private final BookStore bookStore;
    private final Scanner scanner;

    private final String ISBN_10_PATTERN = "^[0-9]{10}$";
    private final Pattern ISBN_10_REGEX = Pattern.compile(ISBN_10_PATTERN);

}
