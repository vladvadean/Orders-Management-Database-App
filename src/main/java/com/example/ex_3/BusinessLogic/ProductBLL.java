package com.example.ex_3.BusinessLogic;


import com.example.ex_3.DataAccess.ProductDAO;
import com.example.ex_3.Model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Product bll.
 */
public class ProductBLL {
    private final ProductDAO productDAO;

    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Find customer by id product.
     *
     * @param id the id
     * @return the product
     */
    public Product findCustomerById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Select all list.
     *
     * @return the list
     */
    public List<Product> selectAll() {
        List<Product> products = productDAO.findAll();
        if (products == null) {
            throw new NoSuchElementException("There are no products in the table!");
        }
        return products;
    }

    /**
     * Insert product.
     *
     * @param id      the id
     * @param name    the name
     * @param quality the quality
     */
    public void insertProduct(int id, String name, int quality) {
        Product pr=new Product(id,name,quality);
        int success = productDAO.insertObject(pr);
        if (success == 0) {
            throw new NoSuchElementException("The product could not be inserted in the table!");
        }
    }

    /**
     * Delete product by id int.
     *
     * @param id the id
     * @return the int
     */
    public int deleteProductById(int id) {
        int success = productDAO.deleteById(id);
        if (success == 0) {
            throw new NoSuchElementException("The product can't be deleted");
        }
        return success;
    }

    /**
     * Delete product by name int.
     *
     * @param name the name
     * @return the int
     */
    public int deleteProductByName(String name) {
        int success = productDAO.deleteByField("name", name);
        if (success == 0) {
            throw new NoSuchElementException("The product can't be deleted");
        }
        return success;
    }

    /**
     * Delete product by quantity int.
     *
     * @param quantity the quantity
     * @return the int
     */
    public int deleteProductByQuantity(int quantity) {
        int success = productDAO.deleteByField("quantity", String.valueOf(quantity));
        if (success == 0) {
            throw new NoSuchElementException("The product can't be deleted");
        }
        return success;
    }


    /**
     * Update product quantity int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateProductQuantity(String changeValue, String conditionValue) {
        int success = productDAO.updateField("quantity", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The product wasn't updated");
        }
        return success;
    }

    /**
     * Update product name int.
     *
     * @param changeValue    the change value
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateProductName(String changeValue, String conditionValue) {
        int success = productDAO.updateField("name", changeValue, "id", conditionValue);
        if (success == 0) {
            throw new NoSuchElementException("The product wasn't updated");
        }
        return success;
    }
}
