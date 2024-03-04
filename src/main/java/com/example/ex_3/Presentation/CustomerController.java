package com.example.ex_3.Presentation;

import com.example.ex_3.BusinessLogic.CustomerBLL;
import com.example.ex_3.DataAccess.CustomerDAO;
import com.example.ex_3.Model.Customer;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The type Customer controller.
 */
public class CustomerController {
    private final CustomerView view;
    private final CustomerBLL customerBLL;
    private final CustomerDAO customerDAO;

    /**
     * Instantiates a new Customer controller.
     *
     * @param view the view
     */
    public CustomerController(CustomerView view) {
        this.view = view;
        this.customerBLL = new CustomerBLL();
        this.customerDAO = new CustomerDAO();

        view.getBtnInsert().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = view.getTfName().getText();
                int id = Integer.parseInt(view.getTfId().getText());
                String email = view.getTfEmail().getText();
                String address = view.getTfEmail().getText();
                customerBLL.insertCustomer(id, name, email, address);
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnUpdateName().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                String name = view.getTfName().getText();
                customerBLL.updateCustomerName(name, String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnUpdateAddress().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                String address = view.getTfAddress().getText();
                customerBLL.updateCustomerAddress(address,String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });
        view.getBtnUpdateEmail().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                String email = view.getTfEmail().getText();
                customerBLL.updateCustomerEmail(email,String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnDelete().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                customerBLL.deleteCustomerById(id);
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnSelectAll().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Customer> customers = customerBLL.selectAll();
                try {
                    displayProducts(customers);
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
        view.getTfName().setText("");
        view.getTfEmail().setText("");
        view.getTfAddress().setText("");
    }
    private void displayProducts(List<Customer> customers) throws IllegalAccessException {
        DefaultTableModel tableModel = customerDAO.createTable(customers);
        view.setTable(tableModel);
    }
}
