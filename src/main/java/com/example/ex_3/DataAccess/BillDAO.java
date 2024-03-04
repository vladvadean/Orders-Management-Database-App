package com.example.ex_3.DataAccess;

import com.example.ex_3.Connection.ConnectionFactory;
import com.example.ex_3.Model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Bill dao.
 */
public class BillDAO extends AbstractDAO<Bill>{
    private String insertQuery(int id, int customerId, int productId, int quantity) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO log");
        sb.append(" VALUES (").append(id).append(", ").append(customerId).append(", ").append(productId).append(", ").append(quantity).append(")");
        System.out.println(sb);
        return sb.toString();
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
        Connection connection = null;
        PreparedStatement statement = null;
        int result;
        String query = insertQuery(id, customerId, productId, quantity);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println("BillDAO insertBill:" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    private String selectAll() {
        return "SELECT " + " * " + " FROM log ";
    }

    public List<Bill> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = selectAll();
        List<Bill> bills = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerid = resultSet.getInt("customerid");
                int productid = resultSet.getInt("productid");
                int quantity = resultSet.getInt("quantity");
                Bill aux = new Bill(id, customerid, productid, quantity);
                bills.add(aux);
            }
            return bills;
        } catch (SQLException e) {
            System.out.println("BIllDAO:selectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}