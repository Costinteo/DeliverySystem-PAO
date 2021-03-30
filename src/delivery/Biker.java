package delivery;

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
}
