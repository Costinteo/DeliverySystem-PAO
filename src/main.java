import delivery.*;

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
        Serviciu.showMenu(deliveryCompanyMap.get("Glovo"));
    }
}
