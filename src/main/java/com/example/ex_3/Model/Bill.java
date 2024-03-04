package com.example.ex_3.Model;

/**
 * The type Bill.
 */
public record Bill(int id, int customerId, int productId, int quantity) {
    @Override
    public String toString() {
        return "Bill: " + "id:" + id + ", customerId=" + customerId + ", productId=" + productId + ", quantity=" + quantity;
    }
}
