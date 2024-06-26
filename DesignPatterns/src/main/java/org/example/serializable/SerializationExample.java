package org.example.serializable;

import java.io.*;

public class SerializationExample {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);

        // 序列化对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("person.ser")))) {
            oos.writeObject(person);
            System.out.println("Serialization Done.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化对象
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("person.ser")))) {
            Person deserializedPerson = (Person) ois.readObject();
            System.out.println("Deserialization Done.");
            System.out.println("Deserialized Person: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
