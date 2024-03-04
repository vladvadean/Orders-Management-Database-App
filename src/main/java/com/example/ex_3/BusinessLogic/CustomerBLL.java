package com.example.ex_3.BusinessLogic;


import com.example.ex_3.DataAccess.CustomerDAO;
import com.example.ex_3.Model.Customer;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Customer bll.
 */
public class CustomerBLL {
    private final CustomerDAO customerDAO;

    /**
     * Instantiates a new Customer bll.
     */
    public CustomerBLL() {
        this.customerDAO = new CustomerDAO();
    }

    /**
     * Find customer by id customer.
     *
     * @param id the id
     * @return the customer
     */
    public Customer findCustomerById(int id) {
        Customer st = customerDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The customer with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Select all list.
     *
     * @return the list
     */
    public List<Customer> selectAll() {
        List<Customer> customers = customerDAO.findAll();
        if (customers == null) {
            throw new NoSuchElementException("There are no customers in the table!");
        }
        return customers;
    }

    /**
     * Insert customer.
     *
     * @param id      the id
     * @param name    the name
     * @param email   the email
     * @param address the address
     */
//work on the inserts
    public void insertCustomer(int id, String name, String email, String address) {
        Customer cs=new Customer(id,name,email,address);
        int success=customerDAO.insertObject(cs);
        if(success==0)
            System.out.println("The customer could not be inserted!");
    }

    /**
     * Delete customer by id int.
     *
     * @param id the id
     * @return the int
     */
    public int deleteCustomerById(int id) {
        int success = customerDAO.deleteById(id);
        if (success == 0) {
            throw new NoSuchElementException("The customer can't be deleted");
        }
        return success;
    }

    /**
     * Delete customer by name int.
     *
     * @param name the name
     * @return the int
     */
    public int deleteCustomerByName(String name) {
        int success = customerDAO.deleteByField("name", name);
        if (success == 0) {
            throw new NoSuchElementException("The customer can't be deleted");
        }
        return success;
    }

    /**
     * Delete customer by email int.
     *
     * @param email the email
     * @return the int
     */
    public int deleteCustomerByEmail(String email) {
        int success = customerDAO.deleteByField("email", email);
        if (success == 0) {
            throw new NoSuchElementException("The customer can't be deleted");
        }
        return success;
    }

    /**
     * Delete customer by address int.
     *
     * @param address the address
     * @return the int
     */
    public int deleteCustomerByAddress(String address) {
        int success = customerDAO.deleteByField("address", address);
        if (success == 0) {
            throw new NoSuchElementException("The customer can't be deleted");
        }
        return success;
    }

    /**
     * Update customer name int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateCustomerName(String changeValue, String conditionValue) {
        int success = customerDAO.updateField("name", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The customer wasn't updated");
        }
        return success;
    }

    /**
     * Update customer address int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateCustomerAddress(String changeValue, String conditionValue) {
        int success = customerDAO.updateField("address", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The customer wasn't updated");
        }
        return success;
    }

    /**
     * Update customer email int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateCustomerEmail(String changeValue, String conditionValue) {
        int success = customerDAO.updateField("email", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The customer wasn't updated");
        }
        return success;
    }
}
