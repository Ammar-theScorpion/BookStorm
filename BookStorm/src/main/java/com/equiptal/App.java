package com.equiptal;

import java.util.Scanner;

import com.equiptal.BookCURD.BookCURD;
import com.equiptal.BookCURD.BookCURDFile;
import com.equiptal.BookCURD.BookCURDInterface;
import com.equiptal.BookShelf.BookDisplayer;
import com.equiptal.BookShelf.BookStore;
import com.equiptal.BookUIX.BookUI;

public class App {

    public App(BookUI bookUI) {
        this.bookUI = bookUI;
        this.bookUI.run();
    }

    public static void main(String[] args) {
        BookDisplayer bookDisplayer = new BookDisplayer();
        BookCURDInterface bookCURD = storageType();

        BookStore bookStore = new BookStore(bookDisplayer, bookCURD);
        BookUI bookUI = new BookUI(bookStore);

        new App(bookUI);
    }

    private static BookCURDInterface storageType() {
        BookCURDInterface type = new BookCURD();

        System.out.println("------------------------------------------------");
        System.out.println("Chose storage type\n 1: on file system\n otherwise: on memory");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("1"))
            type = new BookCURDFile("test.txt");
        return type;
    }

    private BookUI bookUI;
}
