package delivery;

import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant extends Establishment{
    private ArrayList<Recipe> recipeList;

    public Restaurant(String name, String address) {
        super(name, address);
        recipeList = new ArrayList<Recipe>();
    }

    @Override
    String getType() {
        return "Restaurant";
    }

    public void addRecipe(Recipe r) {
        r.setProducer(this.getName());
        recipeList.add(r);
    }

    public void removeRecipe(Recipe r) {
        recipeList.remove(r);
    }

    public void removeRecipe(int index) {
        if (index < recipeList.size())
            recipeList.remove(index);
    }

    public void removeRecipe(String recipeName) {
        recipeList.removeIf(rec -> rec.getName().equals(recipeName));
    }

    public Recipe getRecipe(String recipeName) {
        for (Recipe rec : recipeList) {
            if (rec.getName().toLowerCase().equals(recipeName.toLowerCase()))
                return rec;
        }
        return new Recipe();
    }

    @Override
    public String toString() {
        return super.toString() + "Restaurant{" +
                "recipeList=" + Arrays.toString(recipeList.toArray()) +
                '}';
    }
}
