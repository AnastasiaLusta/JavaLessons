package step.learning.oop;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library{
    private List<Literature> funds;
    public Library() {
        funds = new ArrayList<>();
    }
    /**
     * /
     * adds new instance of literature to collection
     */
    public void add(Literature literature){
        funds.add(literature);
    }

    /**
     * prints all objects in collection
     */
    public void printFunds(){
        for (Literature literature : funds){
            if (literature instanceof Printable){
               ((Printable) literature).print();
            }
            else{
                System.out.println("Can't print this fund: " + literature.getTitle());
            }
        }
    }

    /**
     * prints only objects with method print in class
     */
    public void showPrintable(){
        System.out.println("Printable: ");
        for (Literature literature : funds){
            if (literature instanceof Printable)
            {
                ((Printable) literature).print();
            }
        }
    }

    /**
     * prints only objects with interface Periodic
     */
    public void showPeriodic(){
        System.out.println("Periodic: ");
        for (Literature literature : funds){
            if (literature instanceof Periodic)
            {
                if (literature instanceof Printable)
                    ((Printable) literature).print();
                else{
                    System.out.println("Unprintable: " + literature.getTitle());
                }
            }

        }
    }

    /**
     * prints only objects without interface Periodic
     */
    public void showNonPeriodic(){
        System.out.println("NonPeriodic: ");
        for (Literature literature : funds){
            if (!(literature instanceof Periodic))
            {
                if (literature instanceof Printable)
                    ((Printable) literature).print();
                else{
                    System.out.println("Unprintable: " + literature.getTitle());
                }
            }

        }
    }

    /**
     * prints titles of objects without print method in class
     */
    public void showUnprintable(){
        System.out.println("Unprintable:");
        for (Literature literature : funds){
            if (!(literature instanceof Printable))
            {
                System.out.println(literature.getTitle());
            }
        }
    }
    public void Run(){
        // creating objects of classes Book and Journal
        add(new Book().setAuthor("Knuth").setTitle("Art of programming"));
        add(new Book().setAuthor("Green").setTitle("Looking for Alaska"));
        add(new Journal().setTitle("Vogue").setNumber(23));
        add(new Journal().setTitle("Bazaar").setNumber(11));
        add(new Hologram().setTitle("Pectoral"));
        add(new Poster().setTitle("Poster №1"));
        add(new Poster().setTitle("Poster №2"));
        try {
            add(new Newspaper().setTitle("New York Times").setDate("2022-12-21"));
            add(new Newspaper().setTitle("Washington Post").setDate("2021-09-14"));
            add(new Newspaper().setTitle("Washington Post").setDate("2022-09-22"));
            add(new Newspaper().setTitle("Washington Post").setDate("2022-09-21"));

        } catch (ParseException ex) {
            System.out.println("Funds creation failed: " + ex.getMessage());
            return;
        }

//        printFunds(); // print all objects
//        showPrintable();
//        showUnprintable();
        showPeriodic();
        showNonPeriodic();
    }
}
