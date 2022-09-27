package step.learning.annotations;

import java.util.Random;

public class ClassWithoutAnnotation {
    public int field1;
    public String field2;

    public void method1(){}

    public void method2(){}

    public ClassWithoutAnnotation(){
        var random = new Random();
        field1 = random.nextInt()%100;
        field2 = String.valueOf(random.nextInt()%100);
    }
}
