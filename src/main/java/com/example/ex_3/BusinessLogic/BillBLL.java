package com.example.ex_3.BusinessLogic;

import com.example.ex_3.DataAccess.BillDAO;
import com.example.ex_3.Model.Bill;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Bill bll.
 */
public class BillBLL {
    private final BillDAO billDAO;

    /**
     * Instantiates a new Bill bll.
     */
    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    /**
     * Select all list.
     *
     * @return the list
     */
    public List<Bill> selectAll() {
        List<Bill> bills = billDAO.findAll();
        if (bills == null) {
            throw new NoSuchElementException("There are no bills in the table!");
        }
        return bills;
    }

    /**
     * Insert bill int.
     *
     * @param id         the id
     * @param customerId the customer id
     * @param productId  the product id
     * @param quantity   the quantity
     * @return the int
     */
    public int insertBill(int id, int customerId, int productId, int quantity) {
        int success = billDAO.insertBill(id, customerId, productId, quantity);
        if (success == 0) {
            throw new NoSuchElementException("The bill can not be inserted in the table!");
        }
        return success;
    }
}
