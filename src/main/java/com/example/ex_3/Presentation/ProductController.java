package com.example.ex_3.Presentation;

import com.example.ex_3.BusinessLogic.ProductBLL;
import com.example.ex_3.DataAccess.ProductDAO;
import com.example.ex_3.Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

/**
 * The type Product controller.
 */
public class ProductController {
    private ProductView view;
    private ProductBLL productBLL;
    private ProductDAO productDAO;

    /**
     * Instantiates a new Product controller.
     *
     * @param view the view
     */
    public ProductController(ProductView view) {
        this.view = view;
        this.productBLL = new ProductBLL();
        this.productDAO = new ProductDAO();

        view.getBtnInsert().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = view.getTfName().getText();
                int id = Integer.parseInt(view.getTfId().getText());
                int quantity = Integer.parseInt(view.getTfQuantity().getText());
                productBLL.insertProduct(id, name, quantity);
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnUpdateName().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                String name = view.getTfName().getText();
                productBLL.updateProductName(name, String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnUpdateQuantity().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                int quantity = Integer.parseInt(view.getTfQuantity().getText());
                productBLL.updateProductQuantity(String.valueOf(quantity), String.valueOf(id));
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnDelete().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getTfId().getText());
                productBLL.deleteProductById(id);
                view.resetOutputArea();
                refreshOutput();
            }
        });

        view.getBtnSelectAll().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Product> products = productBLL.selectAll();
                try {
                    displayProducts(products);
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
        view.getTfQuantity().setText("");
    }

    private void displayProducts(List<Product> products) throws IllegalAccessException {
        DefaultTableModel tabelModel=productDAO.createTable(products);
        view.setTable(tabelModel);
    }
}
