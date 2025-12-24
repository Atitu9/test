public class Sale {

    private int saleId;
    private String customerName;
    private double totalAmount;
    private String date;
    private String status;

    public Sale(int saleId, String customerName, double totalAmount, String date, String status) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status;
    }
    public int getSaleId() {
        return saleId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void addItem(double price) {
        if (price > 0) {
            totalAmount += price;
        }
    }
    public void completeSale() {
        status = "Completed";
    }
    @Override
    public String toString() {
        return "Sale{saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

