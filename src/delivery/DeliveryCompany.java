package delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class DeliveryCompany extends Establishment {
    private ArrayList<Establishment> establishmentList;
    private ArrayList<Driver> driverList;
    private ArrayList<User> userList;
    private ArrayList<Order> orderList;

    public DeliveryCompany(String name, String address) {
        super(name, address);
        establishmentList = new ArrayList<>();
        driverList = new ArrayList<>();
        userList = new ArrayList<>();
        orderList = new ArrayList<>();
    }

    @Override
    String getType() {
        return "Delivery Company";
    }

    public void addDriver(Driver d) {
        driverList.add(d);
    }

    public void removeDriver(Driver d) {
        driverList.remove(d);
    }

    public void addEstablishment(Establishment e) {
        establishmentList.add(e);
    }

    public void removeEstablishment(Establishment e) {
        establishmentList.remove(e);
    }

    public void addUser(User u) {
        userList.add(u);
    }

    public void removeUser(User u) {
        userList.remove(u);
    }

    public boolean userCredentialsCheck(String username, String pass) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(pass))
                return true;
        }
        return false;
    }

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        return new User();
    }

    public Product getProduct(String productName) {
        // this is where I suffer again because my class structure sucks
        for (Establishment e : establishmentList) {
            switch(e.getType()) {
                case "Restaurant":
                    Restaurant r = (Restaurant) e;
                    if (r.getRecipe(productName).getName().toLowerCase().equals(productName))
                        return r.getRecipe(productName);
                    break;
                case "Supermarket":
                    Supermarket s = (Supermarket) e;
                    if (s.getFood(productName).getName().toLowerCase().equals(productName))
                        return s.getFood(productName);
                    else if (s.getClothing(productName).getName().toLowerCase().equals(productName))
                        return s.getClothing(productName);
                    break;
                case "Pharmacy":
                    Pharmacy p = (Pharmacy) e;
                    if (p.getDrug(productName).getName().toLowerCase().equals(productName))
                        return p.getDrug(productName);
                    break;
                default:
                    // I didn't know how to return an empty object to signal the lack of such object in the database
                    // so I returned an object of a derived class which I can later check if it has its attributes undefined
                    // I don't like this but, you know how it is
                    return new Recipe();
            }
        }
        return new Recipe();
    }

    public Establishment getEstablishment(int index) {
        return establishmentList.get(index);
    }

    public int getEstablishmentListSize() {
        return establishmentList.size();
    }

    public void receiveOrder(Order o) {
        orderList.add(o);
    }

    public void readDriversFromFile(BufferedReader br) {
        try {
            String line = br.readLine();
            String[] driverData = line.split(",");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // to deliver order, we simply remove the items ordered from the corresponding establishments
    public void deliverOrder() {
        if (orderList.size() > 0) {
            Order o = orderList.get(0);
            // iterate items in order
            for (int i = 0; i < o.getItemListSize(); i++) {
                for (int j = 0; j < establishmentList.size(); j++) {
                    // we search for the establishment from which the user ordered
                    if (o.getProduct(i).getProducer().equals(establishmentList.get(j).getName())) {
                        // this is where I suffer because I didn't realise I need an abstract remove method
                        // if this doesn't somehow uses references I'll scream
                        switch (establishmentList.get(j).getType()) {
                            case "Restaurant":
                                Restaurant r = (Restaurant) establishmentList.get(j);
                                r.removeRecipe(o.getProduct(i).getName());
                                break;
                            case "Supermarket":
                                Supermarket s = (Supermarket) establishmentList.get(j);
                                if (o.getProduct(i).getType().equals("Food"))
                                    s.removeFood(o.getProduct(i).getName());
                                else if (o.getProduct(i).getType().equals("Clothing"))
                                    s.removeClothing(o.getProduct(i).getName());
                                break;
                            case "Pharmacy":
                                Pharmacy p = (Pharmacy) establishmentList.get(j);
                                p.removeProduct(o.getProduct(i).getName());
                                break;
                            default:
                                // there should be an error message here
                                break;

                        }
                    }
                }
            }
            orderList.remove(0);
        }
    }

    @Override
    public String toString() {
        return "DeliveryCompany{" +
                "establishmentList=" + Arrays.toString(establishmentList.toArray()) +
                ", driverList=" + driverList +
                ", userList=" + userList +
                ", orderList=" + orderList +
                '}';
    }
}
