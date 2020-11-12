package com.codecool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BasketCalculation {

    Double finalePrice = 0.0;

    HashMap<String, Integer> groupedBasket = new HashMap<>();
    MyFileReader myFileReader = new MyFileReader("basketPath", "ProductsPath");
    HashMap<String, Integer> stacksValue = new HashMap<String, Integer>();

    public BasketCalculation(MyFileReader myFileReader) {
        this.myFileReader = myFileReader;
        myFileReader.getProductsDiscountFromFile();
        getStacksValue();

    }

    public Double getFinaleBill(List<String> basketFromFile) {
        String codeNumber;
        String packingAmount;
        groupBasket(basketFromFile);
        Iterator basketIterator = groupedBasket.entrySet().iterator();
        while (basketIterator.hasNext()) {
            Map.Entry basketProduct = (Map.Entry) basketIterator.next();
            String thisProduct = (String) basketProduct.getKey();
            String[] data;
            if (thisProduct.contains("-")) {
                data = thisProduct.split("-");
                packingAmount = data[0];
                codeNumber = data[1];
            } else {
                packingAmount = "1";
                codeNumber = thisProduct;
            }
            iterateThroughInstructions(codeNumber, packingAmount, basketProduct);
        }
        System.out.println("Your bill: " + finalePrice + "$");

        return finalePrice;
    }

    private void iterateThroughInstructions(String codeNumber, String packingAmount, Map.Entry basketProduct) {
        HashMap<Object[], Double> productsInstruction = myFileReader.getProductsInstruction();
        Iterator discountsIterator = productsInstruction.entrySet().iterator();
        while (discountsIterator.hasNext()) {
            Map.Entry instructions = (Map.Entry) discountsIterator.next();
            String[] temp = (String[]) instructions.getKey();
            if (codeNumber.equals(temp[0]) && packingAmount.equals(temp[2])) {
                System.out.print(temp[1] + " " + (Double) instructions.getValue() * (Integer) basketProduct.getValue() + "$ ");
                finalePrice = finalePrice + (Double) instructions.getValue() * (Integer) basketProduct.getValue();
            }
        }

        System.out.println(basketProduct.getKey() + "   * " + basketProduct.getValue());
    }

    private HashMap<String, Integer> groupBasket(List<String> basketFromFile) {
        for (String product : basketFromFile) {
            Integer packageSize = getStackFromProduct(product);
            if (groupedBasket.containsKey(product) && packageSize > 1) {
                Integer amountInBasket = groupedBasket.get(product) + 1;
                if (amountInBasket < packageSize) {
                    groupedBasket.replace(product, amountInBasket);
                } else {
                    groupedBasket.remove(product);
                    if (groupedBasket.containsKey(packageSize + "-" + product)) {
                        groupedBasket.replace(packageSize + "-" + product, groupedBasket.get(packageSize + "-" + product) + 1);
                    } else {
                        groupedBasket.put(packageSize + "-" + product, 1);
                    }
                }
            } else {
                if (groupedBasket.containsKey(product)) {
                    groupedBasket.replace(product, groupedBasket.get(product) + 1);
                } else {
                    groupedBasket.put(product, 1);
                }
            }

        }
        return groupedBasket;
    }


    public HashMap<String, Integer> getStacksValue() {
        HashMap<Object[], Double> stack = myFileReader.getProductsInstruction();
        Iterator iterator = stack.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry product = (Map.Entry) iterator.next();
            String[] temp = (String[]) product.getKey();
            if (!temp[2].equals("1")) {
                stacksValue.put(temp[0], Integer.valueOf(temp[2]));
            }
        }
        return stacksValue;
    }

    public Integer getStackFromProduct(String product) {
        Iterator iterator = stacksValue.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            if (item.getKey().equals(product)) {
                return (Integer) item.getValue();
            }
        }
        return 1;
    }

    public Double getFinalePrice() {
        return finalePrice;
    }
}
