package step.learning.services;

import java.util.Random;
import javax.inject.Singleton;
@Singleton
public class RandomProviderMax implements RandomProvider{
    private final int n = new Random().nextInt();

    public int getInt(){
        return n;
    }
}
