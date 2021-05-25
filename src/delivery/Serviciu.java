package delivery;

import utility.Pair;
import utility.SQLdb;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;

public class Serviciu {

    public static void logAction(String action) {
        long millis = System.currentTimeMillis();
        java.util.Date timestamp = new java.util.Date(millis);
        try {
            FileWriter fw = new FileWriter("log.csv", true);
            fw.write(action + "," + timestamp + "\n");
            fw.close();
        }
        catch (IOException e) {
            System.out.print(e);
        }
    }

    // overloaded logAction if you wish to log in another file than the default "log.csv"
    public static void logAction(String action, String filename) {
        long millis = System.currentTimeMillis();
        java.util.Date timestamp = new java.util.Date(millis);
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(action + "," + timestamp + "\n");
            fw.close();
        }
        catch (IOException e) {
            System.out.print(e);
        }
    }

    public static void readEstablishment(BufferedReader br, Establishment e) {
        e.readFromFile(br);
    }

    public static void readPerson(BufferedReader br, Person p) {
        p.readFromFile(br);
    }

    public static void readProduct(BufferedReader br, Product p) {
        p.readFromFile(br);
    }

    public static void readDeliveryCompany(BufferedReader br, DeliveryCompany dc) {
        dc.readFromFile(br);
    }

    // method to read all establishments (including Delivery Companies)
    public static void readAllEstablishments(HashMap<String, DeliveryCompany> dm, HashMap<String, Establishment> em) {
        try {
            File deliveryFile = new File("delivery.csv");
            File restaurantFile = new File("restaurant.csv");
            File supermarketFile = new File("supermarket.csv");
            File pharmacyFile = new File("pharmacy.csv");

            ArrayList<Pair<File, BufferedReader>> fileArray = new ArrayList<>();

            BufferedReader deliveryCompanyReader = new BufferedReader(new FileReader(deliveryFile));
            BufferedReader restaurantReader = new BufferedReader(new FileReader(restaurantFile));
            BufferedReader supermarketReader = new BufferedReader(new FileReader(supermarketFile));
            BufferedReader pharmacyReader = new BufferedReader(new FileReader(pharmacyFile));

            fileArray.add(new Pair<File, BufferedReader>(restaurantFile, restaurantReader));
            fileArray.add(new Pair<File, BufferedReader>(supermarketFile, supermarketReader));
            fileArray.add(new Pair<File, BufferedReader>(pharmacyFile, pharmacyReader));

            for (int i = 0; i < Files.lines(deliveryFile.toPath()).count(); i++) {
                DeliveryCompany dc = new DeliveryCompany("default", "default");
                dc.readFromFile(deliveryCompanyReader);
                dm.put(dc.getName(), dc);
            }

            for (int i = 0; i < fileArray.size(); i++) {
                for (int j = 0; j < Files.lines(fileArray.get(i).first.toPath()).count(); j++) {
                    Establishment e;
                    switch (fileArray.get(i).first.getName()) {
                        case "restaurant.csv" -> e = new Restaurant("default", "default");
                        case "supermarket.csv" -> e = new Supermarket("default", "default");
                        case "pharmacy.csv" -> e = new Pharmacy("default", "default");
                        default -> throw new Exception("Unknown file detected!");
                    }
                    e.readFromFile(fileArray.get(i).second);
                    em.put(e.getName(), e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method used to populate the arrays in Establishment objects (without Delivery Companies)
    public static void populateEstablishmentsWithProducts(HashMap<String, Establishment> em) {

        try {
            File clothingFile = new File("clothing.csv");
            File drugFile = new File("drug.csv");
            File foodFile = new File("food.csv");
            File recipeFile = new File("recipe.csv");

            ArrayList<Pair<File, BufferedReader>> fileArray = new ArrayList<>();

            BufferedReader clothingReader = new BufferedReader(new FileReader(clothingFile));
            BufferedReader drugReader = new BufferedReader(new FileReader(drugFile));
            BufferedReader foodReader = new BufferedReader(new FileReader(foodFile));
            BufferedReader recipeReader = new BufferedReader(new FileReader(recipeFile));

            fileArray.add(new Pair<File, BufferedReader>(clothingFile, clothingReader));
            fileArray.add(new Pair<File, BufferedReader>(drugFile, drugReader));
            fileArray.add(new Pair<File, BufferedReader>(foodFile, foodReader));
            fileArray.add(new Pair<File, BufferedReader>(recipeFile, recipeReader));

            for (int i = 0; i < fileArray.size(); i++) {
                for (int j = 0; j < Files.lines(fileArray.get(i).first.toPath()).count(); j++) {
                    Product p;
                    switch (fileArray.get(i).first.getName()) {
                        case "clothing.csv" -> p = new Clothing();
                        case "drug.csv" -> p = new Drug();
                        case "food.csv" -> p = new Food();
                        case "recipe.csv" -> p = new Recipe();
                        default -> throw new Exception("Unknown file detected!");
                    }
                    p.readFromFile(fileArray.get(i).second);
                    String eType = em.get(p.getProducer()).getType();

                    // again with the same problem: I don't have an abstract add / remove method
                    // could definitely improve this but right now I'm too lazy to replace everything
                    switch(eType) {
                        case "Restaurant" -> ((Restaurant)em.get(p.getProducer())).addRecipe((Recipe)p);
                        case "Supermarket" -> {
                            String pType = p.getType();
                            Supermarket s = (Supermarket) em.get(p.getProducer());
                            switch (pType) {
                                case "Clothing" -> s.addClothing((Clothing) p);
                                case "Food" -> s.addFood((Food) p);
                            }
                        }
                        case "Pharmacy" -> ((Pharmacy)em.get(p.getProducer())).addProduct((Drug)p);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method used to populate the delivery company with drivers
    public static void populateWithDrivers(HashMap<String, DeliveryCompany> dc) {

        try {
            File carFile = new File("cardriver.csv");
            File bikeFile = new File("biker.csv");

            ArrayList<Pair<File, BufferedReader>> fileArray = new ArrayList<>();

            BufferedReader carReader = new BufferedReader(new FileReader(carFile));
            BufferedReader bikeReader = new BufferedReader(new FileReader(bikeFile));

            fileArray.add(new Pair<File, BufferedReader>(carFile, carReader));
            fileArray.add(new Pair<File, BufferedReader>(bikeFile, bikeReader));

            for (int i = 0; i < fileArray.size(); i++) {
                for (int j = 0; j < Files.lines(fileArray.get(i).first.toPath()).count(); j++) {
                    Driver d;
                    switch (fileArray.get(i).first.getName()) {
                        case "cardriver.csv" -> d = new CarDriver("default", 0, 0, 0, "default");
                        case "biker.csv" -> d = new Biker("default", 0, 0, 0, "default");
                        default -> throw new Exception("Unknown file detected!");
                    }
                    d.readFromFile(fileArray.get(i).second);
                    dc.forEach((key, value) -> value.addDriver(d));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerUser(DeliveryCompany dc, User u) {
        System.out.print("Name: ");
        Scanner fin = new Scanner(System.in);
        u.setName(fin.nextLine());
        System.out.print("Age: ");
        u.setAge(Integer.parseInt(fin.nextLine()));
        System.out.print("Address: ");
        u.setAddress(fin.nextLine());
        System.out.print("Username: ");
        u.setUsername(fin.nextLine());
        System.out.print("Password: ");
        u.setPassword(fin.nextLine());
        dc.addUser(u);
    }

    public static void signUser(DeliveryCompany dc, User u) {
        System.out.print("Username: ");
        Scanner fin = new Scanner(System.in);
        String username = fin.nextLine();
        System.out.print("Password: ");
        String pass = fin.nextLine();
        if (dc.userCredentialsCheck(username, pass)) {
            System.out.print("User " + username + " signed in successfully\n");
            User foundUser = dc.getUser(username);
            u.setName(foundUser.getName());
            u.setAge(foundUser.getAge());
            u.setAddress(foundUser.getAddress());
            u.setUsername(foundUser.getUsername());
            u.setPassword(foundUser.getPassword());
        } else {
            System.out.print("User not found, would you like to register? (y/n)\n");
            String ch = fin.next();
            if (ch.equals("y")) {
                registerUser(dc, u);
            }
        }

    }

    public static void searchProduct(DeliveryCompany dc) {
        System.out.print("Product to search for: ");
        Scanner fin = new Scanner(System.in);
        String p = fin.nextLine();
        p = p.toLowerCase();
        if (dc.getProduct(p).getName().equals("undefined"))
            System.out.print("Product not found!\n");
        else System.out.print(dc.getProduct(p).toString() + "\n");

    }

    public static void viewEstablishments(DeliveryCompany dc) {
        System.out.print("Establishments:\n");
        for (int i = 0; i < dc.getEstablishmentListSize(); i++) {
            System.out.print(dc.getEstablishment(i).getName() + ", ");
        }
        System.out.print("\n");

    }

    public static void viewEstablishmentStock(DeliveryCompany dc) {
        System.out.print("Input Establishment to show:\n");
        Scanner fin = new Scanner(System.in);
        String e = fin.nextLine();
        e = e.toLowerCase();
        for (int i = 0; i < dc.getEstablishmentListSize(); i++) {
            if (dc.getEstablishment(i).getName().toLowerCase().equals(e)) {
                System.out.print(dc.getEstablishment(i).toString() + "\n");
                return;
            }
        }
        System.out.print("Establishment not found!\n");

    }

    public static void addProduct(DeliveryCompany dc, User u) {
        System.out.print("Input product(s) to add to cart: ");
        Scanner fin = new Scanner(System.in);
        String str = fin.nextLine();
        str = str.toLowerCase();
        String[] products = str.split("\\s");
        for (int i = 0; i < products.length; i++) {
            if (dc.getProduct(products[i]).getName().toLowerCase().equals(products[i])) {
                u.addToCart(dc.getProduct(products[i]));
            }
        }

    }

    public static void removeProduct(DeliveryCompany dc, User u) {
        System.out.print("Input product(s) to remove from cart: ");
        Scanner fin = new Scanner(System.in);
        String str = fin.nextLine();
        str = str.toLowerCase();
        String[] products = str.split("\\s");
        for (int i = 0; i < products.length; i++) {
            if (u.getProduct(products[i]).getName().toLowerCase().equals(products[i])) {
                u.removeFromCart(dc.getProduct(products[i]));
            }
        }

    }

    public static void viewCart(DeliveryCompany dc, User u) {
        System.out.print("Current cart is:\n");
        for (int i = 0; i < u.getItemList().size(); i++) {
            System.out.print(u.getItemList().get(i).toString() + " ");
        }
        System.out.print("\n");

    }

    public static void sendOrder(DeliveryCompany dc, User u) {
        u.sendOrder(dc);
        System.out.print("Order sent!\n");
        dc.deliverOrder();
    }

    public static void showMenu(DeliveryCompany dc) {
        User u = new User();
        System.out.print("Delivery Demo\nFor choosing an action, type the corresponding action sentence, it matches all cases. That means it's not case sensitive\n\n");

        while (u.getName().equals("undefined")) {
            System.out.print("\nAvailable Actions:\n");
            System.out.print("Sign in\n");
            System.out.print("Register\n");
            System.out.print("Type action: ");
            Scanner fin = new Scanner(System.in);
            String act = fin.nextLine();
            act = act.toLowerCase();
            switch (act) {
                case "sign in" -> {
                    signUser(dc, u);
                    logAction(act);
                }
                case "register" -> {
                    registerUser(dc, u);
                    logAction(act);
                }
                default -> System.out.print("\nInvalid action! Please check spelling.\n");
            }
        }
        boolean flag = true;
        while (flag) {
            System.out.print("\nAvailable Actions:\n");
            System.out.print("Search Product\n");
            System.out.print("View Establishments\n");
            System.out.print("View Establishment Stock\n");
            System.out.print("Add Product to Cart\n");
            System.out.print("Remove Product from Cart\n");
            System.out.print("View Cart\n");
            System.out.print("Send Order\n");
            System.out.print("Exit\n");
            Scanner fin = new Scanner(System.in);
            String act = fin.nextLine();
            act = act.toLowerCase();
            switch (act) {
                case "search product" -> {
                    searchProduct(dc);
                    logAction(act);
                }
                case "view establishments" -> {
                    viewEstablishments(dc);
                    logAction(act);
                }
                case "view establishment stock" -> {
                    viewEstablishmentStock(dc);
                    logAction(act);
                }
                case "add product to cart" -> {
                    addProduct(dc, u);
                    logAction(act);
                }
                case "remove product from cart" -> {
                    removeProduct(dc, u);
                    logAction(act);
                }
                case "view cart" -> {
                    viewCart(dc, u);
                    logAction(act);
                }
                case "send order" -> {
                    sendOrder(dc, u); // send order doesn't really do anything except for send the order and save it for the respective delivery company
                    logAction(act);
                }
                case "exit" -> flag = false;
                default -> System.out.print("\nInvalid action! Please check spelling.\n");
            }
        }

    }


}
