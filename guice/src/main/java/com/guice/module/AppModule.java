package com.guice.module;

import com.google.inject.AbstractModule;
import com.guice.Shape.Shape;
import com.guice.Shape.Square;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Shape.class).to(Square.class);
    }

}
