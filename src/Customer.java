public class Customer {

    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;
    private int loyaltyPoints;

    public Customer(int customerId, String name, String membershipLevel, double totalPurchases, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.totalPurchases = totalPurchases;
        this.loyaltyPoints = loyaltyPoints;
    }
    public Customer() {
        this.customerId = 0;
        this.name = "Unknown Customer";
        this.membershipLevel = "Regular";
        this.totalPurchases = 0.0;
        this.loyaltyPoints = 0;
    }
    public int getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String getMembershipLevel() {
        return membershipLevel;
    }
    public double getTotalPurchases() {
        return totalPurchases;
    }
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
    public void setTotalPurchases(double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public void addPurchase(double amount) {
        if (amount > 0) {
            totalPurchases += amount;
            loyaltyPoints += (int) amount / 100;
        }
    }
    public boolean isVIP() {
        return loyaltyPoints >= 100;
    }
    @Override
    public String toString() {
        return "Customer{customerId=" + customerId +
                ", name='" + name + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", totalPurchases=" + totalPurchases +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}

