package delivery;

import java.util.ArrayList;

public class Order {
    private String user;
    private String address;
    private ArrayList<Product> itemList;
    private double price;

    Order(User u) {
        this.user = u.getName();
        this.address = u.getAddress();
        this.itemList.addAll(u.getItemList());
        double totalPrice = 0;
        for (Product p : itemList) {
            totalPrice += p.getPrice();
        }
        this.price = totalPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct(int index) {
        return itemList.get(index);
    }

    public int getItemListSize() {
        return itemList.size();
    }
}
