package ecommerce.model;

import java.math.BigDecimal;

public class Order {

    private final String email;
    private String userId, orderId;
    private BigDecimal amount;

    public Order(String userId, String orderId, BigDecimal amount , String email) {
        this.email = email;
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
