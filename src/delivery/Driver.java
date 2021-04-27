package delivery;

import java.io.BufferedReader;

public abstract class Driver extends Person {
    private double rating;
    private int timesRated;

    Driver(String name, int age, double rating, int timesRated) {
        super(name, age);
        this.rating = rating;
        this.timesRated = timesRated;
    }

    @Override
    public String getType() {
        return "DRIVER";
    }

    public double getRating() {
        return rating;
    }

    public int getTimesRated() {
        return timesRated;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTimesRated(int timesRated) {
        this.timesRated = timesRated;
    }

    // rating out of 5
    public void addRating(int rating) {
        if (rating < 1)
            rating = 1;
        else if (rating > 5)
            rating = 5;
        this.rating = (this.rating * this.timesRated + rating) / (this.timesRated + 1);
        this.timesRated++;
    }

    public abstract void readFromFile(BufferedReader br);
}
