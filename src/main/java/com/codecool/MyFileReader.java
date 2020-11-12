package com.codecool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {

    public List<String> basket = new ArrayList<String>();
    public HashMap<Object[], Double> productsInstruction = new HashMap<>();
    private final String basketPath;
    private final String productsPath;


    public MyFileReader(String basketPath, String productsPath) {
        this.basketPath = basketPath;
        this.productsPath = productsPath;
    }


    public List<String> getBasketFromFile() {
        try {
            File myObj = new File(basketPath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                basket.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return basket;
    }

    public HashMap<Object[], Double> getProductsDiscountFromFile() {
        try {
            File myObj = new File(productsPath);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");
                String[] key = {data[0].replace(" ", ""), data[1].replace(" ", ""), data[2].replace(" ", "")};
                productsInstruction.put(key, Double.valueOf(data[3]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return productsInstruction;
    }

    public List<String> getBasket() {
        return basket;
    }

    public HashMap<Object[], Double> getProductsInstruction() {
        return productsInstruction;
    }

    public String getBasketPath() {
        return basketPath;
    }

    public String getProductsPath() {
        return productsPath;
    }
}
