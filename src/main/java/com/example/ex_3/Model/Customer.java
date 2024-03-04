package com.example.ex_3.Model;

/**
 * The type Customer.
 */
public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;

    /**
     * Instantiates a new Customer.
     */
    public Customer(){}

    /**
     * Instantiates a new Customer.
     *
     * @param id      the id
     * @param name    the name
     * @param email   the email
     * @param address the address
     */
    public Customer(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer:" + "id=" + id + ", name='" + name + ", email='" + email + ", address='" + address + '\'';
    }
}
