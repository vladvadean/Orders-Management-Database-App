package com.example.ex_3.BusinessLogic;

import com.example.ex_3.DataAccess.CustomerDAO;
import com.example.ex_3.DataAccess.OrderDAO;
import com.example.ex_3.DataAccess.ProductDAO;
import com.example.ex_3.Model.Orders;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Order bll.
 */
public class OrderBLL {
    private final OrderDAO orderDAO;

    /**
     * Instantiates a new Order bll.
     */
    public OrderBLL() {
        this.orderDAO = new OrderDAO();
    }

    /**
     * Find customer by id orders.
     *
     * @param id the id
     * @return the orders
     */
    public Orders findCustomerById(int id) {
        Orders st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Select all list.
     *
     * @return the list
     */
    public List<Orders> selectAll() {
        List<Orders> orders = orderDAO.findAll();
        if (orders == null) {
            throw new NoSuchElementException("There are no orders in the table!");
        }
        return orders;
    }

    /**
     * Insert order.
     *
     * @param id         the id
     * @param customerId the customer id
     * @param productId  the product id
     * @param quantity   the quantity
     */
    public void insertOrder(int id, int customerId, int productId, int quantity) {
        if (quantity < 0) {
            throw new NoSuchElementException("The quantity is not valid and the order wasn't created");
        }
        int success = orderDAO.insertOrder(id, customerId, productId, quantity);
        if (success == 0) {
            throw new NoSuchElementException("The order can not be inserted in the table!");
        }
    }

    /**
     * Delete order by id int.
     *
     * @param id the id
     * @return the int
     */
    public int deleteOrderById(int id) {
        int success = orderDAO.deleteById(id);
        if (success == 0) {
            throw new NoSuchElementException("The order can't be deleted");
        }
        return success;
    }

    /**
     * Delete order by customer id int.
     *
     * @param customerId the customer id
     * @return the int
     */
    public int deleteOrderByCustomerId(int customerId) {
        int success = orderDAO.deleteByField("customerid", String.valueOf(customerId));
        if (success == 0) {
            throw new NoSuchElementException("The order can't be deleted");
        }
        return success;
    }

    /**
     * Delete order by product id int.
     *
     * @param productId the product id
     * @return the int
     */
    public int deleteOrderByProductId(int productId) {
        int success = orderDAO.deleteByField("productid", String.valueOf(productId));
        if (success == 0) {
            throw new NoSuchElementException("The order can't be deleted");
        }
        return success;
    }

    /**
     * Delete order by quantity int.
     *
     * @param quantity the quantity
     * @return the int
     */
    public int deleteOrderByQuantity(int quantity) {
        int success = orderDAO.deleteByField("quantity", String.valueOf(quantity));
        if (success == 0) {
            throw new NoSuchElementException("The order can't be deleted");
        }
        return success;
    }


    /**
     * Update order id customer int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateOrderIdCustomer(String changeValue, String conditionValue) {
        CustomerDAO customerDAO = new CustomerDAO();
        if (customerDAO.findById(Integer.parseInt(changeValue)) == null) {
            throw new NoSuchElementException("The customer wasn't found and the order wasn't updated");
        }
        int success = orderDAO.updateField("customerId", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The order wasn't updated");
        }
        return success;
    }

    /**
     * Update order id product int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateOrderIdProduct(String changeValue, String conditionValue) {
        int success = orderDAO.updateField("productId", changeValue, "id", conditionValue);
        ProductDAO productDAO = new ProductDAO();
        if (productDAO.findById(Integer.parseInt(changeValue)) == null) {
            throw new NoSuchElementException("The product wasn't found and the order wasn't updated");
        }
        if (success == 0) {
            throw new NoSuchElementException("The order wasn't updated");
        }
        return success;
    }

    /**
     * Update order quantity int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateOrderQuantity(String changeValue, String conditionValue) {
        if (Integer.parseInt(changeValue) < 0) {
            throw new NoSuchElementException("The quantity is not valid and the order wasn't updated");
        }
        int success = orderDAO.updateField("quantity", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The order wasn't updated");
        }
        return success;
    }
}
