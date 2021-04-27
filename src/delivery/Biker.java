package delivery;

import java.io.BufferedReader;
import java.io.IOException;

public class Biker extends Driver {
    String bikeType;

    public Biker(String name, int age, double rating, int timesRated, String bikeType) {
        super(name, age, rating, timesRated);
        this.bikeType = bikeType;
    }

    @Override
    public String getType() {
        return "BIKER";
    }

    public String getBikeType() {
        return this.bikeType;
    }
    @Override
    public void readFromFile(BufferedReader br) {
        try {
            String line = br.readLine();
            String[] bikerData = line.split(",");
            this.setName(bikerData[0]);
            this.setAge(Integer.parseInt(bikerData[1]));
            this.setRating(Double.parseDouble(bikerData[2]));
            this.setTimesRated(Integer.parseInt(bikerData[3]));
            this.bikeType = bikerData[4];

        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
