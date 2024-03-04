package com.example.ex_3.Presentation;

/**
 * The type Main controller.
 */
public class MainController {
    private MainView mainView;

    /**
     * Instantiates a new Main controller.
     *
     * @param mainView the main view
     */
    public MainController(MainView mainView) {
        this.mainView = mainView;

        mainView.getBtnProduct().addActionListener(e -> openProductWindow());
        mainView.getBtnCustomer().addActionListener(e -> openCustomerWindow());
        mainView.getBtnOrder().addActionListener(e -> openOrderWindow());
        mainView.getBtnBill().addActionListener(e -> openBillWindow());
    }

    private void openProductWindow() {
        ProductView productView = new ProductView();
        ProductController productController=new ProductController(productView);
        productView.setVisible(true);
    }

    private void openCustomerWindow() {
        CustomerView customerView = new CustomerView();
        CustomerController customerController=new CustomerController(customerView);
        customerView.setVisible(true);
    }

    private void openOrderWindow() {
        OrderView orderView = new OrderView();
        OrderController orderController=new OrderController(orderView);
        orderView.setVisible(true);
    }

    private void openBillWindow() {
        BillView billView = new BillView();
        BillController billController=new BillController(billView);
        billView.setVisible(true);
    }
}
