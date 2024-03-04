package com.example.ex_3.Presentation;

import javax.swing.*;
import java.awt.*;

/**
 * The type Main view.
 */
public class MainView extends JFrame {
    private JButton btnProduct;
    private JButton btnCustomer;
    private JButton btnOrder;
    private JButton btnBill;

    /**
     * Instantiates a new Main view.
     */
    public MainView() {
        this.setTitle("Main Menu");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        applyDarkModeTheme();
        initComponents();
    }

    private void applyDarkModeTheme() {
        Color background = new Color(30, 30, 30);
        Color foreground = new Color(200, 200, 200);
        Color buttonBackground = new Color(60, 60, 60);
        Color buttonForeground = new Color(200, 200, 200);
        Color buttonBorder = new Color(100, 100, 100);

        UIManager.put("Panel.background", background);
        UIManager.put("Button.background", buttonBackground);
        UIManager.put("Button.foreground", buttonForeground);
        UIManager.put("Button.focus", buttonBackground);
        UIManager.put("Button.select", buttonBackground);
        UIManager.put("Button.highlight", buttonBackground);
        UIManager.put("Button.shadow", buttonBackground);
        UIManager.put("Button.border", BorderFactory.createLineBorder(buttonBorder));
        UIManager.put("Label.foreground", foreground);
    }

    private void initComponents() {
        this.setLayout(new GridLayout(2, 2));

        btnProduct = new JButton("Product");
        btnCustomer = new JButton("Customer");
        btnOrder = new JButton("Order");
        btnBill = new JButton("Bill");

        this.add(btnProduct);
        this.add(btnCustomer);
        this.add(btnOrder);
        this.add(btnBill);
    }

    /**
     * Gets btn product.
     *
     * @return the btn product
     */
    public JButton getBtnProduct() {
        return btnProduct;
    }

    /**
     * Gets btn customer.
     *
     * @return the btn customer
     */
    public JButton getBtnCustomer() {
        return btnCustomer;
    }

    /**
     * Gets btn order.
     *
     * @return the btn order
     */
    public JButton getBtnOrder() {
        return btnOrder;
    }

    /**
     * Gets btn bill.
     *
     * @return the btn bill
     */
    public JButton getBtnBill() {
        return btnBill;
    }
}