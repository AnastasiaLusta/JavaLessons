package step.learning;

import com.google.inject.Guice;
import step.learning.services.ConfigModule;

public class Main {
    public static void main(String[] args) {
//        new App().run();
        var injector = Guice.createInjector(new ConfigModule());
        var app = injector.getInstance(App.class);
        app.run();
    }
}