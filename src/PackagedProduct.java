public class PackagedProduct extends Product {
    private String packagingType;

    public PackagedProduct(int productId, String name, double price, int quantity, String packagingType) {
        super(productId, name, price, quantity);
        this.packagingType = packagingType != null ? packagingType : "Unknown";
    }
    public String getPackagingType() { return packagingType; }
    public void setPackagingType(String packagingType) {
        if (packagingType != null && !packagingType.trim().isEmpty()) this.packagingType = packagingType;
    }
    @Override
    public void displayInfo() {
        System.out.println("Packaged Product: " + name + " | Price: " + price + " KZT | Quantity: " + quantity +
                " | Packaging: " + packagingType);
    }
    public boolean isEcoFriendly() {
        return packagingType.toLowerCase().contains("eco");
    }
}
