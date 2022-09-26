package step.learning.files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FilesDemo {
    Scanner kbScanner = new Scanner(System.in);

    public void run() {
//        fsDemo();
//        isDemo();
        fileNavigator();
    }

    /**
     * Navigate commands for file system. Cd - moves between dirs, Cat - shows file's content
     */
    private void fileNavigator() {
        var previousPath = "./"; // saves path for cd .. or if dir is not exist
        var currentPath = "./"; // current path
        var file = new File(currentPath);
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String item : list) {
                    System.out.println(item);
                }
            }
        }
        System.out.println("test");
        while (true) {
            System.out.printf("%n-->");
            String str = kbScanner.nextLine();
            var where = str.split(" ");

            switch (where[0]) {
                case "cd": {
                    previousPath = currentPath;
                    currentPath += where[1] + "/";
                    file = new File(currentPath);
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            String[] nextList = file.list();
                            if (where[1] == "..") {
                                file = new File(previousPath);
                            }
                            if (nextList != null) {
                                for (String item : nextList) {
                                    System.out.println(item);
                                }
                            }
                        }
                    } else {
                        currentPath = previousPath;
                        System.out.println("Resource is not found");
                    }
                }
                break;
                case "cat": {
                    var path = currentPath + where[1];
                    readFile(path);
                }
                break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

    /**
     * Reads all content from file
     * @param path
     */
    private void readFile(String path){
        String fileContent;
        StringBuilder sb = new StringBuilder();

        try (InputStream reader = new FileInputStream(path)) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                sb.append((char) symbol);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        fileContent = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(fileContent);
    }

    private void isDemo() {
        String fileContent;
        StringBuilder sb = new StringBuilder();
        try (InputStream reader = new FileInputStream("hello2.txt")) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                sb.append((char) symbol);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        fileContent = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(fileContent);

        try (FileWriter writer = new FileWriter("result.txt")) {
            writer.write("Здоровенькі були!");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Write finished");
    }


    private void fsDemo() {
        File file = new File("newItem");
        if (file.isDirectory()) {
            System.out.println(file.getName() + " is existing directory");
            String[] list = file.list();
            if (list != null) {
                for (String item : list) {
                    System.out.println(item);
                }
            } else {
                System.out.println("Error");
            }

            System.out.println("Del? y/...");
            int sym;
            try {
                sym = System.in.read();
            } catch (IOException e) {
                System.out.println("System err:" + e.getMessage());
                return;
            }
            if (sym == 'y') {
                boolean res = file.delete();
                if (res) {
                    System.out.println("Deleted");
                } else {
                    System.out.println("Delete error");
                }
            } else {
                System.out.println("Cancelled");
            }
        } else if (file.isFile()) {
            System.out.println(file.getName() + " is existing file");
            if (file.canRead()) {
                System.out.println("Readable");
            } else {
                System.out.println("Non-readable");
            }
            if (file.canExecute()) {
                System.out.println("Executable");
            } else {
                System.out.println("Non-executable");
            }
            if (file.canWrite()) {
                System.out.println("Writable");
            } else {
                System.out.println("Non-writable");
            }
            System.out.println("Size: " + file.length());
        } else {
            System.out.println(file.getName() + " does not exist");
            boolean res = file.mkdir();
            if (res) {
                System.out.println("Directory created: " + file.getName());
            }
        }
    }
}
