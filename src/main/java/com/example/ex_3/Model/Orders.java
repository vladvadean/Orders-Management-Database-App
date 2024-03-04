package com.example.ex_3.Model;

/**
 * The type Orders.
 */
public class Orders {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;

    /**
     * Instantiates a new Orders.
     */
    public Orders() {
    }

    /**
     * Instantiates a new Orders.
     *
     * @param id         the id
     * @param customerId the customer id
     * @param productId  the product id
     * @param quantity   the quantity
     */
    public Orders(int id, int customerId, int productId, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.customerId = customerId;
        this.productId = productId;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets customer id.
     *
     * @param customerId the customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String toString() {
        return "Order: id: " + id + " customerId: " + customerId + " productId: " + productId + " quantity: " + quantity;
    }

}
