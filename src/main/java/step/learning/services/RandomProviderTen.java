package step.learning.services;

import java.util.Random;

public class RandomProviderTen implements RandomProvider{
    private final int n = new Random().nextInt(10);
    @Override
    public int getInt() {
        return n;
    }
}
