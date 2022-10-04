package step.learning.oop;

import java.io.Serializable;

public class Hologram extends Literature implements Serializable {
    @Override
    public Hologram setTitle(String title) {
        super.setTitle(title);
        return this;
    }
}
