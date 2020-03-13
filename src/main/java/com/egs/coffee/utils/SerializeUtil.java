package com.egs.coffee.utils;

import com.egs.coffee.model.Product;

import java.io.*;
import java.util.List;

public class SerializeUtil {
    private static File file = new File("C:\\Users\\varduhisa\\IdeaProjects\\CoffeeMachine\\src\\main\\java\\com\\egs\\coffee\\file\\product");

    /**
     * This method for serialize Products to file.
     * @param posts
     */
    public static void serializeProduct(List<Product> posts) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method for deserialize Products form file.
     * @return
     */
    public static List<Product> deserializeProduct() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Product>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There is no file with products");
        }
        return null;
    }
}
