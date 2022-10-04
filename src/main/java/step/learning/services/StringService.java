package step.learning.services;

import javax.inject.Inject;

public class StringService {
//    @Inject
//    CharService charService;

    @Inject
    private SymbolService charService;
//    @Inject
//    RandomProvider randomProvider;
    public String getString() {
        return String.format("Hello, %c, World times", charService.getChar());
    }
}
