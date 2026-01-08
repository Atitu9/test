public class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected int quantity;

    public Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        setPrice(price);
        setQuantity(quantity);
        this.name = name != null && !name.trim().isEmpty() ? name : "Unknown";
    }
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
        else this.price = 0;
    }
    public void setQuantity(int quantity) {
        if (quantity >= 0) this.quantity = quantity;
        else this.quantity = 0;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name;
    }
    public void displayInfo() {
        System.out.println("Product: " + name + " | Price: " + price + " KZT | Quantity: " + quantity);
    }
    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + name + " (ID: " + productId +
                ", Price: " + price + " KZT, Quantity: " + quantity + ")";
    }
}
