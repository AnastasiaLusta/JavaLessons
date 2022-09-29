package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;

@DemoClass
public class ThreadingDemo {
    @EntryPoint
    public void demo() {
        var kbScanner = new Scanner(System.in);
        System.out.println("How many threads you want to create?");
        System.out.print("--->");
        var amount = kbScanner.nextInt();
        createThreads(amount);
    }

    /**
     * Creates N Threads and makes them colorful
     */
    private void createThreads(int amount) {
        Object obj = null;
        var rand = new Random();
        var colors = ConsoleColors.class;
        var fields = colors.getDeclaredFields();
        var randColor = 0;
        System.out.println("---------------------------");
        for (var i = 0; i < amount; i++) {
            if (obj == null) {
                try {
                    obj = colors.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            randColor = rand.nextInt() % 10;
            int finalI = i;
            try {
                System.out.print(fields[randColor].get(obj));
            } catch (IllegalAccessException e) {
                System.out.println("Error: "+e.getMessage());
            }
            new Thread(() -> System.out.printf("Thread %d works", finalI)).start();
            System.out.print(ConsoleColors.RESET);
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    /**
     * Shows different variants of starting thread
     */
    private void practiceDemo() {
        new PrinterThread().start();
        new ArgThread("arg1").start();
        var argThread = new ArgThread();
        argThread.setArg("arg2");
        argThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("Anon thread"); // anon method
            }
        }.start();


        new Thread(() -> System.out.println("Arrow in Thread")); // creating thread with arrow func
        new Thread(this::printDemo).start();

        new Thread(new PrintRunnable()).start(); // using Runnable

        new Thread(new Runnable() { // anon method using Runnable
            @Override
            public void run() {
                System.out.println("Anon Runnable");
            }
        }).start();

        var str = "param";

        Runnable runnable = () -> System.out.println("Arrow runnable" + str);
        new Thread(runnable).start();

        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Threading demo");
    }

    /**
     * Method that will start in Thread
     */
    private void printDemo() {
        System.out.println("Print demo method");
    }

    /**
     * Implementing of Runnable interface
     */
    static class PrintRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Print Runnable");
        }
    }

    /**
     * Overriding method from Thread class
     */
    static class PrinterThread extends Thread {
        @Override
        public void run() {
            System.out.println("PrinterThread works");
        }
    }

    /**
     * Shows how to use arguments and threads
     */
    static class ArgThread extends Thread {
        private String arg;

        public void setArg(String arg) {
            this.arg = arg;
        }

        public ArgThread() {
            this.arg = "";
        }

        public ArgThread(String arg) {
            this.arg = arg;
        }

        public String getArg() {
            return arg;
        }

        @Override
        public void run() {
            System.out.println("Arg thread: " + this.arg);
        }

    }
}
