package delivery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Supermarket extends Establishment {
    private ArrayList<Food> foodList;
    private ArrayList<Clothing> clothingList;

    public Supermarket(String name, String address) {
        super(name, address);
        foodList = new ArrayList<>();
        clothingList = new ArrayList<>();
    }

    @Override
    String getType() {
        return "Supermarket";
    }

    public void addFood(Food f) {
        f.setProducer(this.getName());
        foodList.add(f);
    }

    public void removeFood(Food f) {
        foodList.remove(f);
    }

    public void removeFood(int index) {
        foodList.remove(index);
    }

    public void removeFood(String foodName) {
        foodList.removeIf(f -> f.getName().equals(foodName));
    }

    public Food getFood(String foodName) {
        for (Food f : foodList) {
            if (f.getName().toLowerCase().equals(foodName.toLowerCase()))
                return f;
        }
        return new Food();
    }

    public void addClothing(Clothing c) {
        clothingList.add(c);
    }

    public void removeClothing(Clothing c) {
        clothingList.remove(c);
    }

    public void removeClothing(int index) {
        clothingList.remove(index);
    }

    public void removeClothing(String clothingName) {
        clothingList.removeIf(c -> c.getName().equals(clothingName));
    }

    public Clothing getClothing(String clothingName) {
        for (Clothing c : clothingList) {
            if (c.getName().toLowerCase().equals(clothingName.toLowerCase()))
                return c;
        }
        return new Clothing();
    }

    @Override
    public String toString() {
        return super.toString() + "Supermarket{" +
                "foodList=" + Arrays.toString(foodList.toArray()) +
                ", clothingList=" + Arrays.toString(clothingList.toArray()) +
                '}';
    }
}
