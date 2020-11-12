package com.codecool;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileReaderTests {

    static MyFileReader myFileReader = new MyFileReader("src/main/resources/basket.txt", "src/main/resources/products.csv");


    @Test
    public void shouldGetBasketFromFileTest() {
        List<String> expected = myFileReader.getBasketFromFile();
        String[] actual = {"1001",
                "1001",
                "3401",
                "1001",
                "3401",
                "3401",
                "3401",
                "1001",
                "1243",
                "1243"};
        Assert.assertEquals(expected, Arrays.asList(actual));
    }


    @Test
    public void shouldGetProductFromFileTest() {
        HashMap<Object[], Double> products = myFileReader.getProductsDiscountFromFile();
        HashMap<Object[], Double> actual = new HashMap<Object[], Double>();
        Object[] key = {"1001", "beer", "1"};
        actual.put(key, 1.20);
        Assert.assertArrayEquals(products.keySet().stream().findFirst().get(), actual.keySet().stream().findFirst().get());

    }

}
