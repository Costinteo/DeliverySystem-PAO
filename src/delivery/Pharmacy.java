package delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;

public class Pharmacy extends Establishment {
    private TreeSet<Drug> availableProducts;

    public Pharmacy(String name, String address) {
        super(name, address);
        availableProducts = new TreeSet<Drug>();
    }

    @Override
    String getType() {
        return "Pharmacy";
    }

    public void addProduct(Drug d) {
        d.setProducer(this.getName());
        availableProducts.add(d);
    }

    public void removeProduct(Drug d) {
        availableProducts.remove(d);
    }

    public void removeProduct(String drugName) {
        availableProducts.removeIf(d -> d.getName().equals(drugName));
    }

    public Drug getDrug(String drugName) {
        for (Drug d : availableProducts) {
            if (d.getName().toLowerCase().equals(drugName.toLowerCase()))
                return d;
        }
        return new Drug();
    }

    @Override
    public String toString() {
        return super.toString() + "Pharmacy{" +
                "availableProducts=" + availableProducts +
                '}';
    }
}
