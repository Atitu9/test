public class FreshProduct extends Product {
    private int shelfLifeDays; // срок годности в днях

    public FreshProduct(int productId, String name, double price, int quantity, int shelfLifeDays) {
        super(productId, name, price, quantity); // Вызов конструктора родителя
        setShelfLifeDays(shelfLifeDays);
    }

    public int getShelfLifeDays() { return shelfLifeDays; }
    public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays >= 0) this.shelfLifeDays = shelfLifeDays;
        else this.shelfLifeDays = 0;
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
