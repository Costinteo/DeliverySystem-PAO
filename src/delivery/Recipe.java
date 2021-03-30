package delivery;

import java.util.Arrays;

public class Recipe extends Product {
    private String[] ingredients;

    Recipe() {
        super();
    }

    // ingredients example string: "tomatoes olives potatoes"
    public Recipe(String name, double price, String producer, String ingredients) {
        super(name, price, producer);
        this.ingredients = ingredients.split("\\s");
    }

    @Override
    String getType() {
        return "Recipe";
    }

    @Override
    public String toString() {
        return super.toString() + "Recipe{" +
                "ingredients=" + Arrays.toString(ingredients) +
                '}';
    }
}
