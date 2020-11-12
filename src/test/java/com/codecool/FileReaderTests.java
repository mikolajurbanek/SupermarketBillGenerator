package com.codecool;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileReaderTests {

    static MyFileReader myFileReader = new MyFileReader("src/main/resources/basket.txt", "src/main/resources/products.csv");


    @Test
    public void shouldGetBasketFromFileTest(String basketPath){
        List<String> expected = myFileReader.getBasketFromFile();
        String[] actual = {"1001",
                "1001" ,
                "3401" ,
                "1001" ,
                "3401" ,
                "3401" ,
                "3401" ,
                "1001" ,
                "1243" ,
                "1243"};
        Assert.assertEquals(expected, Arrays.asList(actual));
    }


    @Test
    public void shouldGetProductFromFileTest(String productsPath){
        HashMap<Object[], Double> products = myFileReader.getProductsInstruction();
        HashMap<Object[], Double> actual = new HashMap<Object[], Double>();
        Object[] firstPair = {"1001", 1};
        actual.put(firstPair, 1.20);

        Assert.assertEquals(products.get(firstPair), actual.get(firstPair));

    }

}
