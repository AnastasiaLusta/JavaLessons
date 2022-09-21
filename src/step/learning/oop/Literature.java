package step.learning.oop;

public abstract class Literature {
    private String title;

    // getter and setter for title
    public String getTitle() {
        return title;
    }

    public Literature setTitle(String title) {
        this.title = title;
        return this;
    }

    // abstract method that overrides in Book and Journal classes
    public abstract void print();

}
