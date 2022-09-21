package step.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Literature> funds;
    public Library() {
        funds = new ArrayList<>();
    }

    public void add(Literature literature){
        funds.add(literature);
    }

    public void printFunds(){
        for (Literature literature : funds){
            literature.print();
        }
    }
    public void Run(){
        // creating objects of classes Book and Journal
        add(new Book().setAuthor("Knuth").setTitle("Art of programming"));
        add(new Book().setAuthor("Green").setTitle("Looking for Alaska"));
        add(new Journal().setTitle("Vogue").setNumber(23));
        add(new Journal().setTitle("Bazaar").setNumber(11));
        printFunds(); // print all objects
    }
}
