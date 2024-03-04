package com.example.ex_3.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The type Customer view.
 */
public class CustomerView extends JFrame {
    private JTextField tfId;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfAddress;
    private JTextArea outputArea;
    private JButton btnInsert;
    private JButton btnUpdateName;
    private JButton btnUpdateEmail;
    private JButton btnUpdateAddress;
    private JButton btnDelete;
    private JButton btnSelectAll;
    private JTable table;

    /**
     * Instantiates a new Customer view.
     */
    public CustomerView() {
        this.setTitle("Customer Operations");
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
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel lblId = new JLabel("ID");
        JLabel lblName = new JLabel("Name");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblAddress = new JLabel("Address");
        tfId = new JTextField();
        tfName = new JTextField();
        tfEmail = new JTextField();
        tfAddress = new JTextField();
        inputPanel.add(lblId);
        inputPanel.add(tfId);
        inputPanel.add(lblName);
        inputPanel.add(tfName);
        inputPanel.add(lblEmail);
        inputPanel.add(tfEmail);
        inputPanel.add(lblAddress);
        inputPanel.add(tfAddress);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        btnInsert = new JButton("Insert");
        btnUpdateName = new JButton("Update Name");
        btnUpdateEmail = new JButton("Update Email");
        btnUpdateAddress = new JButton("Update Address");
        btnDelete = new JButton("Delete");
        btnSelectAll = new JButton("Show All");
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdateName);
        buttonPanel.add(btnUpdateEmail);
        buttonPanel.add(btnUpdateAddress);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSelectAll);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
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
        private final JTextArea textArea;

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
     * Gets tf email.
     *
     * @return the tf email
     */
    public JTextField getTfEmail() {
        return tfEmail;
    }

    /**
     * Gets tf address.
     *
     * @return the tf address
     */
    public JTextField getTfAddress() {
        return tfAddress;
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
     * Gets btn update email.
     *
     * @return the btn update email
     */
    public JButton getBtnUpdateEmail() {
        return btnUpdateEmail;
    }

    /**
     * Gets btn update address.
     *
     * @return the btn update address
     */
    public JButton getBtnUpdateAddress() {
        return btnUpdateAddress;
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
     * Sets table.
     *
     * @param tableModel the table model
     */
    public void setTable(DefaultTableModel tableModel) {
        table.setModel(tableModel);
    }

}