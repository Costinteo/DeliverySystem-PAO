package delivery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Establishment {
    private String name;
    private String address;

    abstract String getType();

    Establishment(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void readFromFile(BufferedReader br) {
        try {
            String line = br.readLine();
            String[] establishmentData = line.split(",");
            this.name = establishmentData[0];
            this.address = establishmentData[1];
        } catch(IOException e) {
            System.out.print(e);
        }
    }

    @Override
    public String toString() {
        return "Establishment " +
                "name=" + name + ", " +
                "address=" + address + "\n";
    }
}
