package com.example.ex_3.DataAccess;

import com.example.ex_3.Connection.ConnectionFactory;
import com.example.ex_3.Model.Orders;
import com.example.ex_3.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type Order dao.
 */
public class OrderDAO extends AbstractDAO<Orders>{
    private String insertQuery(int id, int customerId, int productId, int quantity) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES(").append(id).append(", ").append(customerId).append(", ").append(productId).append(", ").append(quantity).append(")");
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * Insert order int.
     *
     * @param id         the id
     * @param customerId the customer id
     * @param productId  the product id
     * @param quantity   the quantity
     * @return the int
     */
    public int insertOrder(int id, int customerId, int productId, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        BillDAO billDAO=new BillDAO();
        Product productOrdered;
        int result;
        if (customerDAO.findById(customerId) == null) {
            System.out.println("customer with the id: " + customerId + " does not exist!");
            return 0;
        }
        if (productDAO.findById(productId) == null) {
            System.out.println("product with the id: " + productId + " does not exist!");
            return 0;
        }
        productOrdered = productDAO.findById(productId);
        if (productOrdered.getQuantity() < quantity) {
            System.out.println("product actual quantity: " + productOrdered.getQuantity() + " is less than the quantity demanded: " + quantity + "!");
            return 0;
        }
        productDAO.updateField("quantity", String.valueOf(productOrdered.getQuantity() - quantity), "id", String.valueOf(productOrdered.getId()));
        String query = insertQuery(id, customerId, productId, quantity);
        billDAO.insertBill(id,customerId,productId,quantity);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println("OrderDAO insertOrder:" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
}
