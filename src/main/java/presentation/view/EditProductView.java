package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 21, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 * @Source: https://gitlab.com/utcn_dsrl/pt-reflection-example
 */

public class EditProductView extends JFrame {

    private JTextField textFieldName;
    private JTextField textFieldStock;
    private JButton btnBack;

    /**
     * creates a view where a product can be edited
     */
    public EditProductView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblAddProduct = new JLabel("Add or Edit Product");
        lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAddProduct.setBounds(233, 35, 177, 30);
        this.getContentPane().add(lblAddProduct);

        JLabel lblName = new JLabel("Name");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblName.setBounds(132, 156, 78, 20);
        this.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldName.setColumns(10);
        textFieldName.setBounds(232, 156, 278, 21);
        this.getContentPane().add(textFieldName);

        JLabel lblStock = new JLabel("Stock");
        lblStock.setHorizontalAlignment(SwingConstants.CENTER);
        lblStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblStock.setBounds(132, 214, 78, 20);
        this.getContentPane().add(lblStock);

        textFieldStock = new JTextField();
        textFieldStock.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldStock.setColumns(10);
        textFieldStock.setBounds(232, 214, 278, 21);
        this.getContentPane().add(textFieldStock);

        btnBack = new JButton("Ok");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnBack.setBounds(233, 328, 177, 30);
        this.getContentPane().add(btnBack);
    }

    /**
     * getter for the product name field
     * @return name
     */
    public String getNameField(){
        return textFieldName.getText();
    }

    /**
     * getter for the product stock field
     * @return
     */
    public String getStockField(){
        return textFieldStock.getText();
    }

    /**
     * setter for the product name field
     * @param textFieldName
     */
    public void setTextFieldName(String textFieldName) {
        this.textFieldName.setText(textFieldName);
    }

    /**
     * setter for the product stock field
     * @param textFieldStock
     */
    public void setTextFieldStock(String textFieldStock) {
        this.textFieldStock.setText(textFieldStock);
    }

    /**
     * clears the text fields of this view
     */
    public void refresh(){
        this.setTextFieldName(null);
        this.setTextFieldStock(null);
    }

    /**
     * creates a listener for the 'OK' button
     * @param action
     */
    public void addBackListener(ActionListener action){
        btnBack.addActionListener(action);
    }

    /**
     * shows a pop-up with a message
     * @param message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
