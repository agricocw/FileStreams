import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int NAME_LENGTH = 35;
    private static final int DESCRIPTION_LENGTH = 75;
    private static final int ID_LENGTH = 6;

    private String name;
    private String description;
    private String ID;
    private double cost;

    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    // Getter and setter methods for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // Method to return correctly formatted random product record
    public String toRandomRecord() {
        return String.format("%-" + NAME_LENGTH + "s%-"
                + DESCRIPTION_LENGTH + "s%-"
                + ID_LENGTH + "s%-8.2f", name, description, ID, cost);
    }
}
