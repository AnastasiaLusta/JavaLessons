package step.learning;


import step.learning.files.FilesDemo;
import step.learning.oop.Library;
import step.learning.serial.SerializationDemo;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.GREEN);
//        System.out.println(ConsoleColors.GREEN + "Hello world!");
//        String s1 = new String( "Hello");
//        String s2 = new String( "Hello");
//        if(s1 == s2)
//            System.out.println("==");
//        else
//            System.out.println("!==");
//        new Complex().Run();
//        new DataTypes().Run();
//        new Translater().Run();
//        new Library().run();
//        new SerializationDemo().run();
        new FilesDemo().run();
    }
}