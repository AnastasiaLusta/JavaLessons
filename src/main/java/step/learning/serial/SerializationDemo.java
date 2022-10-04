package step.learning.serial;

import org.w3c.dom.ls.LSOutput;
import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
@DemoClass
public class SerializationDemo {
    @EntryPoint
    public void run(){
//        serialize();
//        deserialize();
        serializeList();
        deserializeList();
    }

    public void serializeList(){
        List<DataObject> list = new ArrayList<>();
        list.add(new DataObject(100, 20.5f, "Hello", "Transient"));
        list.add(new DataObject(110, 20.5f, "Hello", "Transient"));
        list.add(new DataObject(120, 20.5f, "Hello", "Transient"));
        list.add(new DataObject(130, 20.5f, "Hello", "Transient"));
        list.add(new DataObject(140, 20.5f, "Hello", "Transient"));
        try(FileOutputStream file = new FileOutputStream("list.ser")){
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(list);
            oos.flush();

        } catch (IOException e) {
            System.out.println("Error serialization: " + e.getMessage());
            return;
        }
        for (DataObject data : list){
            System.out.println(data);
        }
        System.out.println("Collection serialized");
    }

    public void deserializeList(){
        try(FileInputStream file = new FileInputStream("list.ser")){
            ObjectInputStream ois = new ObjectInputStream(file);
            while(file.available()>0)
            {
                List<DataObject> list = ((List<DataObject>) ois.readObject());
                for (DataObject data : list){
                    System.out.println(data);
                }
            }

        } catch(Exception ex){
            System.out.println("Error deserialization: " + ex.getMessage());
            return;
        }

        System.out.println("Collection deserialized");
    }


    public void deserialize(){
        try(FileInputStream file = new FileInputStream("save.ser")){
            ObjectInputStream ois = new ObjectInputStream(file);
            while(file.available()>0)
            {
                DataObject data = (DataObject) ois.readObject();
                System.out.println(data);
            }

        } catch(Exception ex){
            System.out.println("Error deserialization: " + ex.getMessage());
            return;
        }

        System.out.println("Deserialized");
    }

    public void serialize(){
        DataObject data1 = new DataObject();
        DataObject data2 = new DataObject(10);
        DataObject data3 = new DataObject(10, 20.5f);
        DataObject data4 = new DataObject(10, 20.5f, "Hello", "Transient");

        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
        System.out.println(data4);

        try(FileOutputStream file = new FileOutputStream("save.ser")){
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(data1);
            oos.writeObject(data2);
            oos.writeObject(data3);
            oos.writeObject(data4);
            oos.flush();

        } catch (IOException e) {
            System.out.println("Error serialization: " + e.getMessage());
            return;
        }
        System.out.println("Serialized");
    }
}
