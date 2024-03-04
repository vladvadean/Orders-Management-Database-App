package com.example.ex_3.Model;

/**
 * The type Product.
 */
public class Product {
    private int id;
    private String name;
    private int quantity;

    /**
     * Instantiates a new Product.
     */
    public Product(){}

    /**
     * Instantiates a new Product.
     *
     * @param id       the id
     * @param name     the name
     * @param quantity the quantity
     */
    public Product(int id,String name,int quantity){
        this.id=id;
        this.name=name;
        this.quantity=quantity;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Product: id:" + id + " name: " + name + " quantity: " + quantity;
    }
}
