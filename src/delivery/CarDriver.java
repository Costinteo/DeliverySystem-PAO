package delivery;

import java.io.BufferedReader;
import java.io.IOException;

public class CarDriver extends Driver {
    private String carCompany;
    private String carYear;
    private String carColour;
    private String carID;

    public CarDriver(String name, int age, double rating, int timesRated, String carInfo) {
        super(name, age, rating, timesRated);
        // carInfo example string: "Tesla 2008 Red B-77-PAO"
        String[] carInfoParsed = carInfo.split("\\s");
        int len = carInfoParsed.length;
        switch (len) {
            case 1:
                this.carCompany = carInfoParsed[0];
                this.carYear = "UNKNOWN YEAR";
                this.carColour = "UNKNOWN COLOUR";
                this.carID = "UNKNOWN ID";
                break;
            case 2:
                this.carCompany = carInfoParsed[0];
                this.carYear = carInfoParsed[1];
                this.carColour = "UNKNOWN COLOUR";
                this.carID = "UNKNOWN ID";
                break;
            case 3:
                this.carCompany = carInfoParsed[0];
                this.carYear = carInfoParsed[1];
                this.carColour = carInfoParsed[2];
                this.carID = "UNKNOWN ID";
                break;
            case 4:
                this.carCompany = carInfoParsed[0];
                this.carYear = carInfoParsed[1];
                this.carColour = carInfoParsed[2];
                this.carID = carInfoParsed[3];
                break;
            default:
                this.carCompany = "UNKNOWN COMPANY";
                this.carYear = "UNKNOWN YEAR";
                this.carColour = "UNKNOWN COLOUR";
                this.carID = "UNKNOWN ID";
                break;
        }

    }

    @Override
    public String getType() {
        return "CAR DRIVER";
    }

    public String getCarInfo() {
        return this.carCompany + " " + this.carYear + " " + this.carColour + " " + this.carID;
    }

    public String getCarCompany() {
        return this.carCompany;
    }

    public String getCarYear() {
        return this.carYear;
    }

    public String getCarColour() {
        return this.carColour;
    }

    public String getCarID() {
        return this.carID;
    }

    public void setCarInfo(String carInfo) {
        String[] carInfoParsed = carInfo.split("\\s");
        int len = carInfoParsed.length;
        switch (len) {
            case 1:
                this.carCompany = carInfoParsed[0];
                this.carYear = "UNKNOWN YEAR";
                this.carColour = "UNKNOWN COLOUR";
                this.carID = "UNKNOWN ID";
                break;
            case 2:
                this.carCompany = carInfoParsed[0];
                this.carYear = carInfoParsed[1];
                this.carColour = "UNKNOWN COLOUR";
                this.carID = "UNKNOWN ID";
                break;
            case 3:
                this.carCompany = carInfoParsed[0];
                this.carYear = carInfoParsed[1];
                this.carColour = carInfoParsed[2];
                this.carID = "UNKNOWN ID";
                break;
            case 4:
                this.carCompany = carInfoParsed[0];
                this.carYear = carInfoParsed[1];
                this.carColour = carInfoParsed[2];
                this.carID = carInfoParsed[3];
                break;
            default:
                this.carCompany = "UNKNOWN COMPANY";
                this.carYear = "UNKNOWN YEAR";
                this.carColour = "UNKNOWN COLOUR";
                this.carID = "UNKNOWN ID";
                break;
        }

    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    @Override
    public void readFromFile(BufferedReader br) {
        try {
            String line = br.readLine();
            String[] carDriverData = line.split(",");
            this.setName(carDriverData[0]);
            this.setAge(Integer.parseInt(carDriverData[1]));
            this.setRating(Double.parseDouble(carDriverData[2]));
            this.setTimesRated(Integer.parseInt(carDriverData[3]));
            String[] carInfo = line.split("\\s");
            this.carCompany = carInfo[0];
            this.carYear = carInfo[1];
            this.carColour = carInfo[2];
            this.carID = carInfo[3];

        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
