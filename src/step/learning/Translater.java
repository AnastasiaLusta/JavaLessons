package step.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translater {
    Map<String, String> mapEngUkr = new HashMap<>();
    Map<String, String> mapUkrEng = new HashMap<>();

    Scanner kbScanner = new Scanner(System.in);
    public Translater(){
        mapEngUkr.put("hello", "привіт");
        mapEngUkr.put("bye", "пока");
        mapUkrEng.put("привіт", "hello");
        mapUkrEng.put("пока", "bye");
    }

    public void Run(){
        showMenu();
    }

    private void showMenu(){
        System.out.printf("1.Show All%n2.Add Word%n3.Translate Eng%n4.Translate Ukr%n0.Exit");
        System.out.println();
        while(true)
        {
            String str = kbScanner.nextLine();
            switch (str){
                case "1": showAllWords(); break;
                case "2": addWord(); break;
                case "3": translateEng(); break;
                case "4": translateUkr(); break;
                case "0": break;
                default:
                    System.out.println("error");
                    break;
            }
        }
    }

    private void translateEng(){
        System.out.print("Input the word in eng:");
        String str = kbScanner.nextLine();
        System.out.println(mapEngUkr.get(str));
    }

    private void translateUkr(){
        System.out.print("Input the word in ukr:");
        String str = kbScanner.nextLine();
        System.out.println(mapUkrEng.get(str));
    }


    private void addWord(){
        System.out.println("Input the word in english:");
        String str = kbScanner.nextLine();
        System.out.println("Input the word in ukrainian:");
        String str2 = kbScanner.nextLine();
        mapEngUkr.put(str, str2);
        mapUkrEng.put(str2, str);
    }

    private void showAllWords(){
        for (String key: mapEngUkr.keySet()){
            System.out.printf("%s - %s%n",key, mapEngUkr.get(key));
        }
    }
}
