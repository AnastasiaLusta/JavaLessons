package step.learning;

import javax.inject.Inject;


import com.google.inject.name.Named;
import jdk.jfr.Name;
import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;
import step.learning.services.RandomProvider;
import step.learning.services.RandomProviderMax;
import step.learning.services.StringService;
import step.learning.services.date.DateService;
import step.learning.services.hash.HashService;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private final StringService stringService;
    private final DateService dateService;
    private final RandomProvider randomProvider;
    private final HashService hash128;
    private final HashService hash160;

    @Inject
    @Named("MsConnectionString")
    private String MsConnectionString;

    @Inject
    @Named("OracleConnectionString")
    private String OracleConnectionString;

    @Inject
    public App(DateService timeService, StringService stringService, @Named("ten") RandomProvider randomProvider, @Named("128") HashService hash128, @Named("160") HashService hash160) {
        this.randomProvider = randomProvider;
        this.stringService = stringService;
        this.dateService = timeService;
        this.hash128 = hash128;
        this.hash160 = hash160;
    }

    public void run() {
        System.out.printf("DateService date - %s time - %s", dateService.getDate(), dateService.getTime());
        System.out.println("Ioc Demo");
        System.out.println("StringService: " + stringService.getString());
        System.out.println("Random:" + randomProvider.getInt());
        System.out.println("Hash service (128bit)" + hash128.hash("Hello"));
        System.out.println("Hash service (160bit)" + hash160.hash("Hello"));
        System.out.println("MsConnectionString:" + MsConnectionString);
        System.out.println("OracleConnectionString:" + OracleConnectionString);
        runMenu();
    }

    public void runMenu() {
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT);
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
