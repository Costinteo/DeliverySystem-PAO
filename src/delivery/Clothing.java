package delivery;

public class Clothing extends Product {
    private String material;
    private String size;
    private String gender;

    // clothingInfo string example: "cotton M female"
    public Clothing(String name, double price, String producer, String clothingInfo) {
        super(name, price, producer);
        String[] parsedInfo = clothingInfo.split("\\s");
        this.material = parsedInfo[0];
        this.size = parsedInfo[1];
        this.gender = parsedInfo[2];
        // this could also be made like the others with a switch case that sets the unmentioned attributes to "UNKNOWN"
        // but we presume clothing is sure to have these attributes written already
    }

    public Clothing() {
        super();
        this.material = "undefined";
        this.size = "undefined";
        this.gender = "undefined";
    }

    @Override
    String getType() {
        return "Clothing";
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return super.toString() + "Clothing{" +
                "material='" + material + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                "} ";
    }
}
