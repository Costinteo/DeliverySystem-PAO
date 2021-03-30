package delivery;

import static java.lang.Double.parseDouble;

public class Food extends Product {
    private String expirationDate;
    private double kcal;
    private double carbs;
    private double fiber;
    private double protein;

    // nutritional info example: "312 10.7 20.2 13.3"
    public Food(String name, double price, String producer, String expirationDate, String nutritionalInfo) {
        super(name, price, producer);
        this.expirationDate = expirationDate;
        String[] parsedInfo = nutritionalInfo.split("\\s");
        this.kcal = parseDouble(parsedInfo[0]);
        this.carbs = parseDouble(parsedInfo[1]);
        this.fiber = parseDouble(parsedInfo[2]);
        this.protein = parseDouble(parsedInfo[3]);
    }

    public Food() {
        super();
        this.expirationDate = "undefined";
        this.kcal = 0;
        this.carbs = 0;
        this.fiber = 0;
        this.protein = 0;
    }

    @Override
    String getType() {
        return "Food";
    }

    public String getNutritionalInformation() {
        return this.kcal + " kcal / " + this.carbs + "g carbs / " + this.fiber + "g fiber / " + this.protein + "g protein";
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return super.toString() + "Food{" +
                "expirationDate='" + expirationDate + '\'' +
                ", kcal=" + kcal +
                ", carbs=" + carbs +
                ", fiber=" + fiber +
                ", protein=" + protein +
                '}';
    }
}
