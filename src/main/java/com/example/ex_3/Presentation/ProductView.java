package com.example.ex_3.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The type Product view.
 */
public class ProductView extends JFrame {
    private JTextField tfId;
    private JTextField tfName;
    private JTextField tfQuantity;
    private JTextArea outputArea;
    private JButton btnInsert;
    private JButton btnUpdateName;
    private JButton btnUpdateQuantity;
    private JButton btnDelete;
    private JButton btnSelectAll;
    private JTable table;
    private JScrollPane scrollPane;

    /**
     * Instantiates a new Product view.
     */
    public ProductView() {
        this.setTitle("Product Operations");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        applyDarkModeTheme();
        redirectSystemStreams();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel lblId = new JLabel("ID");
        JLabel lblName = new JLabel("Name");
        JLabel lblQuantity = new JLabel("Quantity");
        tfId = new JTextField();
        tfName = new JTextField();
        tfQuantity = new JTextField();
        inputPanel.add(lblId);
        inputPanel.add(tfId);
        inputPanel.add(lblName);
        inputPanel.add(tfName);
        inputPanel.add(lblQuantity);
        inputPanel.add(tfQuantity);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        btnInsert = new JButton("Insert");
        btnUpdateName = new JButton("Update Name");
        btnUpdateQuantity = new JButton("Update Quantity");
        btnDelete = new JButton("Delete");
        btnSelectAll = new JButton("Select All");
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdateName);
        buttonPanel.add(btnUpdateQuantity);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSelectAll);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 250));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setPreferredSize(new Dimension(600, 150));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.NORTH);
        tablePanel.add(outputScrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(tablePanel, BorderLayout.SOUTH);
    }

    private void applyDarkModeTheme() {
        Color background = new Color(32, 32, 32);
        Color foreground = new Color(224, 224, 224);
        Color buttonBackground = new Color(45, 45, 45);
        Color buttonForeground = new Color(224, 224, 224);
        Color tableSelectionBackground = new Color(60, 60, 60);
        Color tableSelectionForeground = new Color(224, 224, 224);

        UIManager.put("control", background);
        UIManager.put("info", background);
        UIManager.put("nimbusBase", background);
        UIManager.put("nimbusFocus", foreground);
        UIManager.put("nimbusLightBackground", background);
        UIManager.put("nimbusOrange", foreground);
        UIManager.put("nimbusSelectedText", background);
        UIManager.put("nimbusSelectionBackground", tableSelectionBackground);
        UIManager.put("text", foreground);

        UIManager.put("Panel.background", background);
        UIManager.put("Label.foreground", foreground);
        UIManager.put("TextField.background", buttonBackground);
        UIManager.put("TextField.foreground", buttonForeground);
        UIManager.put("Button.background", buttonBackground);
        UIManager.put("Button.foreground", buttonForeground);
        UIManager.put("TextArea.background", background);
        UIManager.put("TextArea.foreground", foreground);
        UIManager.put("Table.background", background);
        UIManager.put("Table.foreground", foreground);
        UIManager.put("Table.selectionBackground", tableSelectionBackground);
        UIManager.put("Table.selectionForeground", tableSelectionForeground);
    }

    private void redirectSystemStreams() {
        PrintStream printStream = new PrintStream(new CustomOutputStream(outputArea));
        System.setOut(printStream);
        System.setErr(printStream);
    }
    private static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        /**
         * Instantiates a new Custom output stream.
         *
         * @param textArea the text area
         */
        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

    /**
     * Reset output area.
     */
    public void resetOutputArea() {
        outputArea.setText("");
    }

    /**
     * Gets tf id.
     *
     * @return the tf id
     */
    public JTextField getTfId() {
        return tfId;
    }

    /**
     * Gets tf name.
     *
     * @return the tf name
     */
    public JTextField getTfName() {
        return tfName;
    }

    /**
     * Gets tf quantity.
     *
     * @return the tf quantity
     */
    public JTextField getTfQuantity() {
        return tfQuantity;
    }

    /**
     * Gets output area.
     *
     * @return the output area
     */
    public JTextArea getOutputArea() {
        return outputArea;
    }

    /**
     * Gets btn insert.
     *
     * @return the btn insert
     */
    public JButton getBtnInsert() {
        return btnInsert;
    }

    /**
     * Gets btn update name.
     *
     * @return the btn update name
     */
    public JButton getBtnUpdateName() {
        return btnUpdateName;
    }

    /**
     * Gets btn update quantity.
     *
     * @return the btn update quantity
     */
    public JButton getBtnUpdateQuantity() {
        return btnUpdateQuantity;
    }

    /**
     * Gets btn delete.
     *
     * @return the btn delete
     */
    public JButton getBtnDelete() {
        return btnDelete;
    }

    /**
     * Gets btn select all.
     *
     * @return the btn select all
     */
    public JButton getBtnSelectAll() {
        return btnSelectAll;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Gets scroll pane.
     *
     * @return the scroll pane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Sets table.
     *
     * @param tableModel the table model
     */
    public void setTable(DefaultTableModel tableModel) {
        table.setModel(tableModel);
    }

    /**
     * Remove table.
     */
    public void removeTable() {
        table.setModel(new DefaultTableModel());
    }
}