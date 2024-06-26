package org.example.serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonSerializationExample {
    public static void main(String[] args) {
        Animal Animal = new Animal("John", 30);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonString = objectMapper.writeValueAsString(Animal);
            System.out.println("Serialization Done.");
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("animal.json"))) {
                fileWriter.write(jsonString);
            }

            Animal deserializedAnimal = objectMapper.readValue(jsonString, Animal.class);
            System.out.println("Deserialization Done.");
            System.out.println("Deserialized Animal: " + deserializedAnimal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
