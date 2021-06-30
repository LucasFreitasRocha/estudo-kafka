package ecommerce.model;

import java.math.BigDecimal;

public class Order {

    private final String email;
    private String  orderId;
    private BigDecimal amount;

    public Order( String orderId, BigDecimal amount, String email) {

        this.orderId = orderId;
        this.amount = amount;
        this.email = email;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", email= " + email +
                '}';
    }
}
