package model;

public abstract class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected int quantity;

    public Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative: " + price);
        this.price = price;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative: " + quantity);
        this.quantity = quantity;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }
    public abstract void displayInfo();

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + name + " (ID: " + productId +
                ", Price: " + price + " KZT, Quantity: " + quantity + ")";
    }
}
