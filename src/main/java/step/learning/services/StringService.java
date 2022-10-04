package step.learning.services;

import javax.inject.Inject;
import javax.inject.Named;

public class StringService {
//    @Inject
//    CharService charService;

    @Inject
    private SymbolService charService;
    @Inject
    @Named("max")
    RandomProvider randomProvider;

    public String getString() {
        return String.format("Hello, %c, World %d times", charService.getChar(), randomProvider.getInt());
    }
}
