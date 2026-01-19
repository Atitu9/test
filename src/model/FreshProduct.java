package model;

public class FreshProduct extends Product {
    private int shelfLifeDays;

    public FreshProduct(int productId, String name, double price, int quantity, int shelfLifeDays) {
        super(productId, name, price, quantity);
        setShelfLifeDays(shelfLifeDays);
    }
    public int getShelfLifeDays() { return shelfLifeDays; }

    public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays < 0) throw new IllegalArgumentException("Shelf life cannot be negative");
        this.shelfLifeDays = shelfLifeDays;
    }
    @Override
    public void displayInfo() {
        System.out.println("Fresh Product: " + name + " | Price: " + price + " KZT | Quantity: " + quantity +
                " | Shelf life: " + shelfLifeDays + " days");
    }
    public boolean isExpiringSoon() {
        return shelfLifeDays <= 3;
    }
}
