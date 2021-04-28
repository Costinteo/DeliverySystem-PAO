package delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

class DrugPrimaryUseComparator implements Comparator<Drug> {
    public int compare(Drug first, Drug second) {
        return first.compareTo(second);
    }
}


public class Pharmacy extends Establishment {
    private Drug[] availableProducts;
    private int availableProductsSize;

    public Pharmacy(String name, String address) {
        super(name, address);
        // this is for stage 1 purposes only
        // we'll assume we can have a maximum of 100 drugs in the pharmacy
        availableProducts = new Drug[100];
        availableProductsSize = 0;
    }

    @Override
    String getType() {
        return "Pharmacy";
    }

    public void sortProducts() {
        DrugPrimaryUseComparator comparator = new DrugPrimaryUseComparator();
        Drug[] slicedArray = Arrays.copyOfRange(availableProducts, 0, availableProductsSize);
        Arrays.sort(slicedArray, comparator);
        for (int i = 0; i < availableProductsSize; i++) {
            availableProducts[i] = slicedArray[i];
        }
    }

    public void addProduct(Drug d) {
        try {
            d.setProducer(this.getName());
            availableProducts[availableProductsSize] = d;
            availableProductsSize++;
            // sorting
            this.sortProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(Drug d) {
        try {
            for (int i = 0; i < availableProductsSize; i++) {
                if (availableProducts[i].getName().equals(d.getName())) {
                    // deleting element found
                    for (int j = i; j < availableProductsSize - 1; j++) {
                        availableProducts[j] = availableProducts[j + 1];
                    }
                    availableProductsSize--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(String drugName) {
        try {
            for (int i = 0; i < availableProductsSize; i++) {
                if (availableProducts[i].getName().equals(drugName)) {
                    // deleting element found
                    for (int j = i; j < availableProductsSize - 1; j++) {
                        availableProducts[j] = availableProducts[j + 1];
                    }
                    availableProductsSize--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Drug getDrug(String drugName) {
        for (int i = 0; i < availableProductsSize; i++) {
            if (availableProducts[i].getName().toLowerCase().equals(drugName.toLowerCase()))
                return availableProducts[i];
        }
        return new Drug();
    }

    @Override
    public String toString() {
        String products = "";
        for (int i = 0; i < availableProductsSize; i++) {
            products += availableProducts[i].toString();
        }
        return super.toString() +
                "availableProducts= [\n" + products + "]\n";
    }
}
