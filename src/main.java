import delivery.*;
import utility.SQLdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class main {
    public static void main(String[] args) {

        HashMap<String, Establishment> establishmentMap = new HashMap<>();
        HashMap<String, DeliveryCompany> deliveryCompanyMap = new HashMap<>();
        Serviciu.readAllEstablishments(deliveryCompanyMap, establishmentMap);
        Serviciu.populateEstablishmentsWithProducts(establishmentMap);

        // now we manually add each establishment to each delivery company
        establishmentMap.forEach((key, value) -> deliveryCompanyMap.forEach((dkey, dvalue) -> dvalue.addEstablishment(value)));
        Serviciu.populateWithDrivers(deliveryCompanyMap);

        // we will be using only the first delivery company to show the menu
        // we presume each delivery company has a menu
        // the only delivery company in the csv is "Glovo"

        // we will read the userlist for delivery comnpanies from an SQL database
        // we presume we only have one delivery company, Glovo, for the purpose of the test
        // either that or the accounts are shared between delivery companies
        SQLdb database = SQLdb.getDatabase();
        ArrayList<User> userList = new ArrayList<>();
        try {
            userList = database.readUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < userList.size(); i++) {
            deliveryCompanyMap.get("Glovo").addUser(userList.get(i));
        }

        Serviciu.showMenu(deliveryCompanyMap.get("Glovo"));

        // at the end of the menu we have to add any newly registered accounts to the database
        for (int i = 0; i < deliveryCompanyMap.get("Glovo").getUserListSize(); i++) {
            User u = deliveryCompanyMap.get("Glovo").getUser(i);
            try {
                boolean alreadyAdded = false;
                for (int j = 0; j < userList.size(); j++) {
                    if (userList.get(j).getUsername() == u.getUsername()) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (!alreadyAdded) {
                    database.addUser(u);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
