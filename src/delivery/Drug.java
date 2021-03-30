package delivery;

public class Drug extends Product implements Comparable<Drug> {
    private String activeSubstance;
    private String primaryUse;
    private boolean isOTC; // over-the-counter drug

    Drug() {
        super();
        this.activeSubstance = "undefined";
        this.primaryUse = "undefined";
        this.isOTC = false;
    }
    public Drug(String name, double price, String producer, String activeSubstance, String primaryUse, boolean isOTC) {
        super(name, price, producer);
        this.activeSubstance = activeSubstance;
        this.primaryUse = primaryUse;
        this.isOTC = isOTC;
    }

    @Override
    String getType() {
        return "Drug";
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public String getPrimaryUse() {
        return primaryUse;
    }

    public void setPrimaryUse(String primaryUse) {
        this.primaryUse = primaryUse;
    }

    public boolean isOverTheCounter() {
        return isOTC;
    }

    public void setOTC(boolean OTC) {
        isOTC = OTC;
    }

    @Override
    // sorts after primaryUse
    public int compareTo(Drug other) {
        if (primaryUse.equals(other.primaryUse))
            return 0;
        else if (primaryUse.compareTo(other.primaryUse) < 0)
            return -1;
        else return 1;
    }

    @Override
    public String toString() {
        return super.toString() + "Drug{" +
                "activeSubstance='" + activeSubstance + '\'' +
                ", primaryUse='" + primaryUse + '\'' +
                ", isOTC=" + isOTC +
                '}';
    }
}
