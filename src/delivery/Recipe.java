package delivery;

import java.io.BufferedReader;
import java.io.IOException;
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
    public void readFromFile(BufferedReader br) {
        try {
            String line = br.readLine();
            String[] recipeData = line.split(",");
            this.setName(recipeData[0]);
            this.setPrice(Double.parseDouble(recipeData[1]));
            this.setProducer(recipeData[2]);
            this.ingredients = recipeData[3].split("\\s");

        } catch (IOException e) {
            System.out.print(e);
        }
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
