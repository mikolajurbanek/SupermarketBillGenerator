package com.codecool;

public class App {

    static MyFileReader myFileReader = new MyFileReader("src/main/resources/basket.txt", "src/main/resources/products.csv");
    static BasketCalculation basketCalculation = new BasketCalculation(myFileReader);

    public static void main(String[] args) {
        basketCalculation.getFinaleBill(myFileReader.getBasketFromFile());
    }


}
