package delivery;

import java.util.ArrayList;
import java.util.Arrays;

public class User extends Person {
    private String address;
    private String username;
    private String password;
    private ArrayList<Product> itemList;

    public User(String name, int age, String address, String username, String password) {
        super(name, age);
        this.address = address;
        this.username = username;
        this.password = password;
        itemList = new ArrayList<Product>();
    }

    public User() {
        super();
        this.address = "undefined";
        this.username = "undefined";
        this.password = "undefined";
        itemList = new ArrayList<>();
    }

    @Override
    String getType() {
        return "User";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addToCart(Product p) {
        itemList.add(p);
    }

    public void removeFromCart(Product p) {
        itemList.remove(p);
    }

    public void removeFromCart(String pName) {
        itemList.removeIf(p -> p.getName().equals(pName));
    }

    public void clearCart() {
        itemList.clear();
    }

    public String getProductName(int index) {
        if (index < itemList.size()) {
            return itemList.get(index).getName();
        } else return "NOT FOUND";
    }

    // unsafe (index could be bigger than size, maybe ArrayList addresses this)
    public Product getProduct(int index) {
        return itemList.get(index);
    }

    public Product getProduct(String productName) {
        for (Product p : itemList) {
            if (p.getName().toLowerCase().equals(productName.toLowerCase())) {
                return p;
            }
        }
        return new Recipe();
        // I hate this but I really don't know how to fix it
        // can't return new Product because it's abstract
    }


    public ArrayList<Product> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", itemList=" + Arrays.toString(itemList.toArray()) +
                '}';
    }

    public void sendOrder(DeliveryCompany dc) {
        Order o = new Order(this);
        this.clearCart();
        dc.receiveOrder(o);
    }

}
