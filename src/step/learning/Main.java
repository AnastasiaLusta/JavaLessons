package step.learning;


import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.GREEN);
        menu();

    }

    /**
     * Shows menu of all available demo classes that have Annotations @DemoClass and @EntryPoint
     */
    private static void menu() {
        var currentClass = Main.class;
        var packageName = currentClass.getPackage().getName();
        List<String> classNames = getClassNames(packageName);
        if (classNames == null) {
            System.out.println("Error scanning package");
            return;
        }
        System.out.println("----------------------------------------------------");
        List<Class<?>> demoClasses = new ArrayList<>();
        for (String className : classNames) {
            Class<?> theClass;
            try {
                theClass = Class.forName(className);
            } catch (Exception ignored) {
                continue;
            }
            if (theClass.isAnnotationPresent(DemoClass.class)) {
                demoClasses.add(theClass); //adds all classes with annotation to list
            }
        }

        var kbScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Demo classes: ");
            int i = 1;
            for (Class<?> demoClass : demoClasses) {
                System.out.printf("%d. %s%n", i++, demoClass.getName()); // shows menu with classes in list
            }
            System.out.println("0. Exit");
            System.out.printf("%n--->");
            var choice = 0;
            try {
                choice = kbScanner.nextInt();
            } catch (InputMismatchException ignored) {
                System.out.println("Incorrect input");
            }
            if (choice == 0) {
                System.out.println("Bye");
                return;
            }

            int index = choice - 1;
            if (index >= demoClasses.size() || index < 0) {
                System.out.println("Incorrect input");
            } else {
                var exeClass = demoClasses.get(index);
                var methods = exeClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(EntryPoint.class)) {
                        try {
                            method.invoke(exeClass.getDeclaredConstructor().newInstance());
                        } catch (Exception ex) {
                            System.out.println("Execution error: " + ex.getMessage());
                        }
                        break;
                    }
                }
            }
        }
    }

    private static List<String> getClassNames(String packageName) {
        var classLoader = Thread.currentThread().getContextClassLoader();
        var packagePath = classLoader.getResource(packageName.replace(".", File.separator)).getPath();
        var packageBase = new File(packagePath);
        var list = packageBase.listFiles();
        List<String> classNames = new ArrayList<>();
        for (File file : list) {
            if (file.isFile()) {
                var fileName = file.getName();
                if (fileName.endsWith(".class")) {
                    classNames.add(packageName + "." + fileName.substring(0, fileName.lastIndexOf(".")));
                }
            }
            if (file.isDirectory()) {
                var subList = file.listFiles();
                for (File subFile : subList) {
                    if (subFile.isFile()) {
                        var fileName = subFile.getName();
                        if (fileName.endsWith(".class")) {
                            classNames.add(packageName + "." + file.getName() + "." + fileName.substring(0, fileName.lastIndexOf(".")));
                        }
                    }
                }

            }
        }
        return classNames;
    }
}