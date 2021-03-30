package delivery;

import java.util.Locale;
import java.util.Scanner;

public class Serviciu {

    public static void registerUser(DeliveryCompany dc, User u) {
        System.out.print("Name: ");
        Scanner fin = new Scanner(System.in);
        u.setName(fin.nextLine());
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
            System.out.print("User" + username + "signed in successfully\n");
            u = dc.getUser(username);
        }
        else  {
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
                case "sign in" -> signUser(dc, u);
                case "register" -> registerUser(dc, u);
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
                case "search product" -> searchProduct(dc);
                case "view establishments" -> viewEstablishments(dc);
                case "view establishment stock" -> viewEstablishmentStock(dc);
                case "add product to cart" -> addProduct(dc, u);
                case "remove product from cart" -> removeProduct(dc, u);
                case "view cart" -> viewCart(dc, u);
                case "send order" -> sendOrder(dc, u); // send order doesn't really do anything except for send the order and save it for the respective delivery company
                case "exit" -> flag = false;
                default -> System.out.print("\nInvalid action! Please check spelling.\n");
            }
        }

    }


}
