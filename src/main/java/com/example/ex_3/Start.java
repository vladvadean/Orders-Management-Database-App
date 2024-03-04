package com.example.ex_3;


import com.example.ex_3.Presentation.MainController;
import com.example.ex_3.Presentation.MainView;

/**
 * The type Start.
 */
public class Start {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MainView view = new MainView();
        new MainController(view);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });
    }
}
