package step.learning;
import java.util.*;

public class Complex {
    private Random random;
    public Complex(){
        random = new Random();
    }
    private void arraysDemo(){
        int []arr1 = new int[4];
        int[]arr2 = new int[]{5,4,3,2,1};
        int[]arr3 = {5,4,3,2,1};
        for (int i = 0; i<4; ++i){
            System.out.print(
                    String.format("arr[%d] = %d", i,arr1[i])
            );
        }
        int j = 0;
        for (; j<arr2.length; j++){
            System.out.print(arr2[j]);
        }
        System.out.println();
        for(int a: arr3){
            System.out.print(a);
        }

        int[][]arr4 = {{1,2,3},
                {4,5,6},{7,8}};
        int[][]arr5 = new int[3][4];
        randArr(arr5);
        printArr(arr5);

        for (int[]a:arr4){
            for(int x:a){
                System.out.print(x);
            }
            System.out.println();
        }
    }
    private void randArr(int[][] arr){
        for (int i = 0; i<arr.length; i++){
            for (int j = 0; j<arr.length; j++){
                arr[i][j] = random.nextInt() % 100;
            }
        }
    }

    private void printArr(int[][] arr){
        for(int i = 0; i<arr.length; i++){
            for (int j = 0; j<arr.length; j++){
                System.out.print(arr[i][j] + " ") ;
            }
            System.out.println();
        }
    }

    private void collectionsDemo(){
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(10);
        arr1.add(20);
        arr1.add(30);
        arr1.add(40);
        for (Integer x: arr1){
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i = 0 ; i<arr1.size(); i++){
            System.out.printf("i = %d, x = %d%n", i, arr1.get(i) );
        }

        Map<String, String> map = new HashMap<>();
        map.put("hello", "привет");
        map.put("bye", "пока");
        for (String key: map.keySet()){
            System.out.printf("%s - %s%n",key, map.get(key));
        }
        Scanner kbScanner = new Scanner(System.in);
        String str = kbScanner.nextLine();

    }
    public void Run(){
//        arraysDemo();
        collectionsDemo();
    }
}
