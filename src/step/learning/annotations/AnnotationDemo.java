package step.learning.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@DemoClass
public class AnnotationDemo {
    @FieldAnnotation(value = "all versions", priority = -1)
    private String sep = "-----------------------------------";

    @EntryPoint
    @MethodAnnotation("Entry Point")
    public void run() {
        Class<?> type = ClassWithAnnotation.class;
        Class<?> thisType = this.getClass();
        Class<?> nameType;
        try {
            nameType = Class.forName("step.learning.annotations.ClassWithoutAnnotation");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }


        checkClassAnnotation(type);
        checkClassAnnotation(thisType);
        checkClassAnnotation(nameType);

        System.out.println(sep);

        showMethodAnnotation(type);
        showMethodAnnotation(thisType);
        showMethodAnnotation(nameType);

        System.out.println(sep);

        showFieldAnnotation(type);
        showFieldAnnotation(thisType);
        showFieldAnnotation(nameType);

        System.out.println(sep);
    }

    /**
     * Checks if class has MarkerAnnotation
     *
     * @param type
     */
    private void checkClassAnnotation(Class<?> type) {
        var marker = type.getAnnotation(MarkerAnnotation.class);
        if (marker != null) {
            System.out.printf("Class %s has MarkerAnnotation%n", type.getName());
        } else {
            System.out.printf("Class %s has no MarkerAnnotation%n", type.getName());
        }
    }

    /**
     * Shows methods that have MethodAnnotation
     *
     * @param type
     */
    private void showMethodAnnotation(Class<?> type) {
        Object obj = null;
        Method[] methods = type.getDeclaredMethods();
        try {
            obj = type.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                var methodAnnotation = method.getAnnotation((MethodAnnotation.class));
                System.out.printf("Method %s of class %s has %s method annotation%n", method.getName(), type.getName(), methodAnnotation.value());
                try {
                    if (methodAnnotation.value().equals("Recommended")) {
                        method.setAccessible(true);
                        method.invoke(obj);
                    } else {
                        System.out.println("It is not recommended!");
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                } catch (InvocationTargetException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.printf("Method %s of class %s has no method annotation%n", method.getName(), type.getName());
            }
        }
    }

    /**
     * Shows fields and their values that have FieldAnnotation
     *
     * @param type
     */
    private void showFieldAnnotation(Class<?> type) {
        Field[] fields = type.getDeclaredFields();
        Object obj = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldAnnotation.class)) {
                var fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                System.out.printf("Field %s of class %s is %s with priority %d%n", field.getName(), type.getName(), fieldAnnotation.value(), fieldAnnotation.priority());
                if (obj == null) {
                    try {
                        obj = type.getDeclaredConstructor().newInstance();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                try {
                    field.setAccessible(true);
                    System.out.println("Value: " + field.get(obj));
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.printf("Field %s of class %s has no annotation%n", field.getName(), type.getName());
            }
        }
    }
}
