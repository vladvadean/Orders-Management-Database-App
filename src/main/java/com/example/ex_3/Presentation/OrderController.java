package com.example.ex_3.Presentation;

import com.example.ex_3.BusinessLogic.CustomerBLL;
import com.example.ex_3.BusinessLogic.OrderBLL;
import com.example.ex_3.DataAccess.CustomerDAO;
import com.example.ex_3.DataAccess.OrderDAO;
import com.example.ex_3.Model.Customer;
import com.example.ex_3.Model.Orders;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The type Order controller.
 */
public class OrderController {
    private final OrderView view;
    private final OrderBLL orderBLL;
    private final OrderDAO orderDAO;

    /**
     * Instantiates a new Order controller.
     *
     * @param view the view
     */
    public OrderController(OrderView view) {
        this.view = view;
        this.orderBLL = new OrderBLL();
        this.orderDAO = new OrderDAO();

        view.getBtnInsert().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idCustomer = Integer.parseInt(view.getTfIdCustomer().getText());
                int id = Integer.parseInt(view.getTfId().getText());
                int idProduct = Integer.parseInt(view.getTfIdProduct().getText());
                int quantity = Integer.parseInt(view.getTfQuantity().getText());
                orderBLL.insertOrder(id, idCustomer, idProduct, quantity);
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnUpdateIdCustomer().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                int idCustomer = Integer.parseInt(view.getTfIdCustomer().getText());
                orderBLL.updateOrderIdCustomer(String.valueOf(idCustomer), String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnUpdateIdProduct().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                int idProduct = Integer.parseInt(view.getTfIdProduct().getText());
                orderBLL.updateOrderIdProduct(String.valueOf(idProduct), String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });
        view.getBtnUpdateQuantity().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                int quantity = Integer.parseInt(view.getTfQuantity().getText());
                orderBLL.updateOrderQuantity(String.valueOf(quantity), String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnDelete().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                orderBLL.deleteOrderById(id);
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnSelectAll().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Orders> orders = orderBLL.selectAll();
                try {
                    displayProducts(orders);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                view.resetOutputArea();
                refreshOutput();
            }
        });
    }

    private void refreshOutput() {
        view.getTfId().setText("");
        view.getTfIdCustomer().setText("");
        view.getTfIdProduct().setText("");
        view.getTfQuantity().setText("");
    }

    private void displayProducts(List<Orders> orders) throws IllegalAccessException {
        DefaultTableModel tableModel = orderDAO.createTable(orders);
        view.setTable(tableModel);
    }
}
