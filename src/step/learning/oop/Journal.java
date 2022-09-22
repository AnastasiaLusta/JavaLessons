package step.learning.oop;

public class Journal extends Literature implements Printable, Periodic{
    private Integer number;

    // getter and setter for number variable
    public Integer getNumber() {
        return number;
    }

    public Journal setNumber(Integer number) {
        this.number = number;
        return this;
    }

    // delegating Literature to setTitle
    public Journal setTitle(String title){
        super.setTitle(title);
        return this;
    }

    // override for print method
    public void print(){
        System.out.printf("Journal. №: %s. Title: %s%n", this.number, super.getTitle());
    }
}
