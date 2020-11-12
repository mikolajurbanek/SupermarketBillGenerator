package com.codecool;

import org.junit.Assert;
import org.junit.Test;

public class BasketCalculationTests {

    MyFileReader myFileReader = new MyFileReader("src/main/resources/basket.txt", "src/main/resources/products.csv");
    BasketCalculation basketCalculation = new BasketCalculation(myFileReader);

    @Test
    public void shouldGetFinalPrice(){
        Double finalePrice = basketCalculation.getFinaleBill(myFileReader.getBasketFromFile());
        Double actual = 17.0;
        Assert.assertEquals(finalePrice, actual);
    }


}
