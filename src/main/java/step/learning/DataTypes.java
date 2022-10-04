package step.learning;

import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

@DemoClass(priority = 1)
public class DataTypes {
    @EntryPoint
    public void Run(){
        byte bx = -100;   // В Java все числовые типы - знаковые (беззнаковых нет)
        byte by = -0x3C;
        short sx = -30000;
        int ix = 20;      // -1 =(bin)= 1111 1111
        long lx = 100L;
        float fx = 0.1f;  // 0.1 - double
        float fy = (float)0.1;
        double dx = 1.5e-7;
        char c = 'A';     // UTF-16 (2 байта)
        boolean b = true;

        Object obj = ix;  // Общий базовый тип
        System.out.println(obj.getClass());
        Integer iy = 10;  // Object wrap for int

        System.out.printf("%d (%s)%n", bx, Byte.TYPE);
        System.out.printf("%d (%s)%n", by, Byte.TYPE);
        System.out.printf("%d (%s)%n", sx, Short.TYPE);
        System.out.printf("%d (%s)%n", ix, Integer.TYPE);
        System.out.printf("%d (%s)%n", lx, Long.TYPE);
        System.out.printf("%f (%s)%n", fx, Float.TYPE);
        System.out.printf("%f (%s)%n", fy, Float.TYPE);
        System.out.printf("%f (%s)%n", dx, Double.TYPE);
        System.out.printf("%s (%s)%n", c, Character.TYPE);
        System.out.printf("%d (%s)%n", obj, obj.getClass());
        System.out.printf("%d (%s)%n", 10, Boolean.TYPE);

    }
}
