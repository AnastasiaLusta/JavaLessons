package step.learning.oop;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    Scanner kbScanner = new Scanner(System.in);
    private List<Literature> funds;

    public Library() {
        funds = new ArrayList<>();
    }

    /**
     * Shows user available actions in library class
     */
    public void menu() {
        System.out.println("Welcome to the library");
        System.out.printf("1.Show all%n2.Show printable%n3.Show periodic%n4.Save%n5.Add fund%n0.Exit");
        while (true) {
            System.out.printf("%n-->");
            String str = kbScanner.nextLine();
            switch (str) {
                case "1":
                    printFunds();
                    break;
                case "2":
                    showPrintable();
                    break;
                case "3":
                    showPeriodic();
                    break;
                case "4":
                    serializeFunds();
                    break;
                case "5":
                    createObject();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }

    /**
     * Adds new fund to the collection
     */
    public void createObject() {
        System.out.println("What do you want to add?");
        System.out.printf("1.Book%n2.Hologram%n3.Journal%n4.Newspaper%n5.Poster%n0.Exit");
        while (true) {
            System.out.printf("%n-->");
            String str = kbScanner.nextLine();
            switch (str) {
                case "1": {
                    while (true) {
                        System.out.printf("%nTitle:");
                        String title = kbScanner.nextLine();
                        System.out.printf("%Author:");
                        String author = kbScanner.nextLine();
                        if (!(title.isEmpty()) || !(author.isEmpty())) {
                            add(new Book().setAuthor(author).setTitle(title));
                            break;
                        }
                    }
                }
                break;
                case "2": {
                    while (true) {
                        System.out.printf("%nTitle:");
                        String title = kbScanner.nextLine();
                        if (!(title.isEmpty())) {
                            add(new Hologram().setTitle(title));
                            break;
                        }
                    }
                }
                break;
                case "3": {
                    while (true) {
                        System.out.printf("%nTitle:");
                        String title = kbScanner.nextLine();
                        System.out.printf("%Number:");
                        String number = kbScanner.nextLine();
                        if (!(title.isEmpty())) {
                            add(new Journal().setTitle(title).setNumber(Integer.valueOf(number)));
                            break;
                        }
                    }
                }
                break;
                case "4": {
                    while (true) {
                        System.out.printf("%nTitle:");
                        String title = kbScanner.nextLine();
                        System.out.printf("%Date:");
                        String date = kbScanner.nextLine();
                        if (!(title.isEmpty()) || !(date.isEmpty())) {
                            try {
                                add(new Newspaper().setTitle(title).setDate(date));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                    }
                } break;
                case "5": {
                    while (true) {
                        System.out.printf("%nPoster:");
                        String title = kbScanner.nextLine();
                        if (!(title.isEmpty())) {
                            add(new Poster().setTitle(title));
                            break;
                        }
                    }
                }
                break;
                default:
                    System.out.println("Error");
                    break;
            }
            if (!(str.isEmpty()))
                break;
        }
    }

    /**
     * /
     * adds new instance of literature to collection
     */
    public void add(Literature literature) {
        funds.add(literature);
    }

    /**
     * Serializes all objects in funds to funds.ser file
     */
    public void serializeFunds() {
        try (FileOutputStream file = new FileOutputStream("funds.ser")) {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(funds);
            oos.flush();

        } catch (IOException e) {
            System.out.println("Error serialization: " + e.getMessage());
            return;
        }
        System.out.println("Collection serialized");
    }

    /**
     * Deserializes all objects from funds.ser file to funds
     */
    public void deserializeFunds() {

        List<Literature> list = new ArrayList<>();
        try (FileInputStream file = new FileInputStream("funds.ser")) {
            ObjectInputStream ois = new ObjectInputStream(file);
            while (file.available() > 0) {

                list = ((List<Literature>) ois.readObject());
            }
        } catch (Exception ex) {
            System.out.println("Error deserialization: " + ex.getMessage());
            return;
        }
        funds = list;
        System.out.println("Amount of objects: " + funds.size()); // shows amount of deserialized objects
        System.out.println("Collection deserialized");
    }

    /**
     * prints all objects in collection
     */
    public void printFunds() {
        for (Literature literature : funds) {
            if (literature instanceof Printable) {
                ((Printable) literature).print();
            } else {
                System.out.println("Can't print this fund: " + literature.getTitle());
            }
        }
    }

    /**
     * prints only objects with method print in class
     */
    public void showPrintable() {
        System.out.println("Printable: ");
        for (Literature literature : funds) {
            if (literature instanceof Printable) {
                ((Printable) literature).print();
            }
        }
    }

    /**
     * prints only objects with interface Periodic
     */
    public void showPeriodic() {
        System.out.println("Periodic: ");
        for (Literature literature : funds) {
            if (literature instanceof Periodic) {
                if (literature instanceof Printable)
                    ((Printable) literature).print();
                else {
                    System.out.println("Unprintable: " + literature.getTitle());
                }
            }

        }
    }

    /**
     * prints only objects without interface Periodic
     */
    public void showNonPeriodic() {
        System.out.println("NonPeriodic: ");
        for (Literature literature : funds) {
            if (!(literature instanceof Periodic)) {
                if (literature instanceof Printable)
                    ((Printable) literature).print();
                else {
                    System.out.println("Unprintable: " + literature.getTitle());
                }
            }

        }
    }

    /**
     * prints titles of objects without print method in class
     */
    public void showUnprintable() {
        System.out.println("Unprintable:");
        for (Literature literature : funds) {
            if (!(literature instanceof Printable)) {
                System.out.println(literature.getTitle());
            }
        }
    }

    public void run() {
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

        deserializeFunds();
        menu();
    }
}
