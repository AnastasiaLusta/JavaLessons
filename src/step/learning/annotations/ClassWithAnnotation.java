package step.learning.annotations;

import java.util.Random;

@MarkerAnnotation
public class ClassWithAnnotation {
    @FieldAnnotation(value = "v1.0", priority = 1)
    public int field1;
    @FieldAnnotation(value = "v1.1", priority = 2)
    public String field2;

    @MethodAnnotation("Deprecated")
    private void method1(){
        System.out.println("-- method1 works");
    }

    @MethodAnnotation("Recommended")
    private void method2(){
        System.out.println("-- method2 works");
    }

    public ClassWithAnnotation(){
        var random = new Random();
        field1 = random.nextInt()%100;
        field2 = String.valueOf(random.nextInt()%100);
    }
}
