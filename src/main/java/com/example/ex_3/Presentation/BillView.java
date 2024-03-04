package com.example.ex_3.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The type Bill view.
 */
public class BillView extends JFrame {
    private JTextArea outputArea;
    private JButton btnSelectAll;
    private JTable table;

    /**
     * Instantiates a new Bill view.
     */
    public BillView() {
        this.setTitle("Bill Operations");
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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        btnSelectAll = new JButton("Select All");
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