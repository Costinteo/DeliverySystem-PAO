package delivery;

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

    @Override
    public String toString() {
        return "Establishment{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
