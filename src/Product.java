public class Product {

    private int productId;
    private String name;
    private double price;
    private int stockQuantity;
    private boolean available;

    public Product(int productId, String name, double price, int stockQuantity, boolean available) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.available = available;
    } ///
    public Product() {
        this.productId = 0;
        this.name = "Unknown Product";
        this.price = 0.0;
        this.stockQuantity = 0;
        this.available = false;
    }
    public int getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setName(String name) {
        this.name = name;
    }  
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean isInStock() {
        return stockQuantity > 0;
    }
    public void restock(int amount) {
        if (amount > 0) {
            stockQuantity += amount;
            available = true;
        }
    }
    @Override
    public String toString() {
        return "Product{productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", available=" + available +
                '}';
    }
}

