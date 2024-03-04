package com.example.ex_3.Presentation;

import com.example.ex_3.BusinessLogic.BillBLL;
import com.example.ex_3.DataAccess.BillDAO;
import com.example.ex_3.Model.Bill;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The type Bill controller.
 */
public class BillController {
    private BillView view;
    private BillBLL billBLL;
    private BillDAO billDAO;

    /**
     * Instantiates a new Bill controller.
     *
     * @param view the view
     */
    public BillController(BillView view) {
        this.view = view;
        this.billBLL = new BillBLL();
        this.billDAO = new BillDAO();


        view.getBtnSelectAll().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Bill> bills = billBLL.selectAll();
                try {
                    displayProducts(bills);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                view.resetOutputArea();
            }
        });
    }

    private void displayProducts(List<Bill> bills) throws IllegalAccessException {
        DefaultTableModel tableModel = billDAO.createTable(bills);
        view.setTable(tableModel);
    }
}
