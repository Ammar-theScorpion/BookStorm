package com.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.guice.Shape.Shape;
import com.guice.module.AppModule;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        Shape shape = injector.getInstance(Shape.class);
        shape.draw();
    }
}
