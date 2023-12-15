package com.equiptal.Module;

import com.equiptal.BookCURD.BookCURD;
import com.equiptal.BookCURD.BookCURDDatabase;
import com.equiptal.BookCURD.BookCURDFile;
import com.equiptal.BookCURD.BookCURDInterface;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    private String type;
    public AppModule(String type){
        this.type = type;
    }
    @Override
    protected void configure() {
        if (type.equals("file")) {
            bind(BookCURDInterface.class).to(BookCURDFile.class);
        } else if (type.equals("database")) {
            bind(BookCURDInterface.class).to(BookCURDDatabase.class);
        } else {
            bind(BookCURDInterface.class).to(BookCURD.class);
        }
    }
}
 