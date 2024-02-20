package src.weddingInventory;

public class Inventory {

    // Private attributes/variables
    private String nameOfItem = " ";
    private int quantity = 0;
    private boolean typeGreeneries = false;
    private boolean typeVases = false;
    private boolean typeTableRunners = false;

    // Default Constructor
    public Inventory() {
    }

    /**
     * Constructor with parameters
     * 
     * @param nameOfItem
     * @param quantity
     * @param typeGreeneries
     * @param typeVases
     * @param typeTableRunners
     */
    public Inventory(String nameOfItem, int quantity, boolean typeGreeneries, boolean typeVases,
            boolean typeTableRunners) {
        this.nameOfItem = nameOfItem;
        this.quantity = quantity;
        this.typeGreeneries = typeGreeneries;
        this.typeVases = typeVases;
        this.typeTableRunners = typeTableRunners;
    }

    /**
     * Get the name of the item
     * 
     * @return name of the item
     */
    public String getNameOfItem() {
        return nameOfItem;
    }

    /**
     * Set the name of the item
     * 
     * @param nameOfItem
     */
    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    /**
     * Get the quantity of the item
     * 
     * @return the quanity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the item
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Number of item must be 0 or more.");
        } else {
            this.quantity = quantity;
        }
    }

    /**
     * Get true/false value of greeneries type
     * 
     * @return true/false value of item
     */
    public boolean isTypeGreeneries() {
        return typeGreeneries;
    }

    /**
     * Set true/false value of greeneries type
     * 
     * @param typeGreeneries
     */
    public void setTypeGreeneries(boolean typeGreeneries) {
        this.typeGreeneries = typeGreeneries;
    }

    /**
     * Get true/false value of vases type
     * 
     * @return true/false value of vases type
     */
    public boolean isTypeVases() {
        return typeVases;
    }

    /**
     * Set true/false value of vases type
     * 
     * @param typeVases
     */
    public void setTypeVases(boolean typeVases) {
        this.typeVases = typeVases;
    }

    /**
     * Get true/false value of table runner type
     * 
     * @return true/false value of table runners type
     */
    public boolean isTypeTableRunners() {
        return typeTableRunners;
    }

    /**
     * Set true/false value of table runner type
     * 
     * @param typeTableRunners
     */
    public void setTypeTableRunners(boolean typeTableRunners) {
        this.typeTableRunners = typeTableRunners;
    }

    /**
     * Returns a string representation of the Inventory object.
     * The string includes the name of the item, its quantity, and the types of
     * items it represents.
     *
     * @return A string representation of the Inventory object.
     */
    @Override
    public String toString() {
        return "Inventory{" +
                "nameOfItem='" + nameOfItem + "', quantity=" + quantity +
                ", typeGreeneries=" + typeGreeneries + ", typeVases=" + typeVases +
                ", typeTableRunners=" + typeTableRunners + '}';
    }

    /**
     * Custom toString() method to print detailed information
     * 
     * @return a string of the newly created item
     */
    public String toStringDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("******** New Inventory ********\n");
        sb.append("Name: ").append(nameOfItem).append("\n");
        sb.append("Quantity: ").append(quantity).append("\n");
        sb.append("Type of Wedding Decor: ");

        if (typeGreeneries) {
            sb.append("Greenery");
        } else if (typeVases) {
            sb.append("Vase");
        } else if (typeTableRunners) {
            sb.append("Table Runner");
        } else {
            sb.append("None");
        }
        return sb.toString();
    }
}
