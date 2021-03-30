import delivery.*;

public class main {
    public static void main(String[] args) {
        DeliveryCompany glovo = new DeliveryCompany("Glovo", "Str Cuza Voda etc");

        Restaurant McD = new Restaurant("McDonalds", "Piata Romana");
        McD.addRecipe(new Recipe("BigTasty", 10, "McDonalds", "cheeseslice burger buns tomato mayo"));
        McD.addRecipe(new Recipe("McWrap", 15, "McDonalds", "chicken tomatoes lettuce wrap"));
        McD.addRecipe(new Recipe("McFlurry", 8, "McDonalds", "vanilla chocolate ice milk"));

        Supermarket Kf = new Supermarket("Kaufland", "Aleea Circului nr 4");
        Kf.addFood(new Food("Apple", 2, "Kaufland", "5/12/2021", "300 12 10 9"));
        Kf.addFood(new Food("Pear", 2, "Kaufland", "5/12/2021", "300 12 10 9"));
        Kf.addFood(new Food("Watermelon", 2, "Kaufland", "5/12/2021", "300 12 10 9"));
        Kf.addClothing(new Clothing("Hat", 5, "Kaufland", "cotton M male"));
        Kf.addClothing(new Clothing("Blazer", 10, "Kaufland", "cotton M male"));
        Kf.addClothing(new Clothing("Blouse", 13, "Kaufland", "cotton S female"));

        Pharmacy Cat = new Pharmacy("Catena", "Blvd Matei Basarab nr 18");
        Cat.addProduct(new Drug("Nurofen", 15, "Catena", "Ibuprofen", "Anti-inflammatory", true));
        Cat.addProduct(new Drug("Brufen", 15, "Catena", "Ibuprofen", "Anti-inflammatory", true));
        Cat.addProduct(new Drug("Paracetamol", 15, "Catena", "Paracetamol", "Analgesic", true));

        glovo.addEstablishment(McD);
        glovo.addEstablishment(Kf);
        glovo.addEstablishment(Cat);

        glovo.addDriver(new CarDriver("Mihai Stan", 24, 3.2, 10, "Mercedes 2008 Red IL-77-MIH"));
        glovo.addDriver(new Biker("Brihac Andrei", 20, 5, 20, "Aluminum"));

        Serviciu.showMenu(glovo);
    }
}
