package com.equiptal;

import com.equiptal.BookCURD.BookCURD;
import com.equiptal.BookCURD.BookCURDFile;
import com.equiptal.BookCURD.BookCURDInterface;
import com.equiptal.BookShelf.Book;
import com.equiptal.BookShelf.BookDisplayer;
import com.equiptal.BookShelf.BookStore;
import com.equiptal.BookUIX.BookUI;
import com.equiptal.Response.Response;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
public class App {

   private static void init(String type){
        BookDisplayer bookDisplayer = new BookDisplayer();
        BookCURDInterface bookCURD = storageType(type);
        BookStore bookStore = new BookStore(bookDisplayer, bookCURD);
        bookUI = new BookUI(bookStore);
   }

    public static void main(String[] args) {
        init("memory");
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);

        }).start(8080);
        app.get("/type/{tp}", ctx -> {
            System.err.println(ctx.pathParam("tp"));
            init(ctx.pathParam("tp"));
            ctx.result("type added");
        });

        app.get("/search/{isbn}", ctx ->{
            Book result = bookUI.processSearch(ctx.pathParam("isbn")) ;
            if(result != null)
                ctx.status(200).json(result);
            else
                ctx.status(404).json(new Response(false, "not found"));
        });       
        app.delete("/delete", ctx ->{

            Book result = bookUI.processDelete(ctx.formParam("isbn"));
            if(result != null)
                ctx.status(200).json(new Response(true, "Item deleted successfully"));
            else
                ctx.status(404).json(new Response(false, "not found"));
        });

        app.post("/add", ctx->{
            System.err.println(ctx.formParam("isbn")+ ctx.formParam("title")+ ctx.formParam("author")+ Double.parseDouble(ctx.formParam("price")));
            bookUI.processBookInput(ctx.formParam("isbn"), ctx.formParam("title"), ctx.formParam("author"), Double.parseDouble(ctx.formParam("price")));
            ctx.status(201).json(new Response(true, "added"));
        });

        app.get("/all", ctx ->{
            System.err.println(bookUI.processDisplayAll());
            ctx.json(bookUI.processDisplayAll());
        });
    }

    private static BookCURDInterface storageType(String type) {
        if(type.equals("file"))
            return new BookCURDFile("test.txt");
        return new BookCURD();

    }

    private static BookUI bookUI;
}
