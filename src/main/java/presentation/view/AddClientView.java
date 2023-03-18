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

public class AddClientView extends JFrame {

    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JTextField textFieldEmail;
    private JButton btnBack;

    /**
     * creates the view where a new client is added
     */
    public AddClientView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblAddClient = new JLabel("Add or Edit Client");
        lblAddClient.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddClient.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAddClient.setBounds(223, 28, 177, 30);
        this.getContentPane().add(lblAddClient);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(123, 116, 78, 20);
        this.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldName.setBounds(223, 116, 278, 21);
        this.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblAddress.setBounds(123, 174, 78, 20);
        this.getContentPane().add(lblAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(223, 174, 278, 21);
        this.getContentPane().add(textFieldAddress);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEmail.setBounds(123, 236, 78, 20);
        this.getContentPane().add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(223, 236, 278, 21);
        this.getContentPane().add(textFieldEmail);

        btnBack = new JButton("Ok");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnBack.setBounds(223, 321, 177, 30);
        this.getContentPane().add(btnBack);
    }

    /**
     * getter for client name field as string
     * @return name
     */
    public String getNameField(){
        return textFieldName.getText();
    }

    /**
     * getter for client address field as string
     * @return address
     */
    public String getAddressField(){
        return textFieldAddress.getText();
    }

    /**
     * getter for client email field
     * @return email
     */
    public String getEmailField(){
        return textFieldEmail.getText();
    }

    /**
     * listener for the 'ok' button
     * @param action
     */
    public void addBackListener(ActionListener action){
        btnBack.addActionListener(action);
    }

    /**
     * setter for client name field
     * @param textFieldName
     */
    public void setTextFieldName(String textFieldName) {
        this.textFieldName.setText(textFieldName);
    }

    /**
     * setter for client address field
     * @param textFieldAddress
     */
    public void setTextFieldAddress(String textFieldAddress) {
        this.textFieldAddress.setText(textFieldAddress);
    }

    /**
     * setter for client email field
     * @param textFieldEmail
     */
    public void setTextFieldEmail(String textFieldEmail) {
        this.textFieldEmail.setText(textFieldEmail);
    }

    /**
     * clears all the text fields
     */
    public void refresh(){
        this.setTextFieldName(null);
        this.setTextFieldAddress(null);
        this.setTextFieldEmail(null);
    }

    /**
     * creates a pop-up with a message
     * @param message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
