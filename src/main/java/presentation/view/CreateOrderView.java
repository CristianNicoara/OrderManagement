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

public class CreateOrderView extends JFrame{

    private JTable clientTable;
    private JTable productTable;
    private JTable orderTable;
    private JTextField textFieldQuantity;
    private JButton btnCreate;
    private JScrollPane clientTableS;
    private JScrollPane productTableS;
    private JScrollPane orderTableS;

    /**
     * creates the view where a new order can be created
     */
    public CreateOrderView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblCreateOrder = new JLabel("Create Order");
        lblCreateOrder.setHorizontalAlignment(SwingConstants.CENTER);
        lblCreateOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCreateOrder.setBounds(231, 11, 162, 30);
        this.getContentPane().add(lblCreateOrder);

        JPanel panel = new JPanel();
        panel.setBounds(33, 73, 276, 149);
        this.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        clientTableS = new JScrollPane();
        GridBagConstraints gbc_clientTable = new GridBagConstraints();
        gbc_clientTable.fill = GridBagConstraints.BOTH;
        gbc_clientTable.gridx = 0;
        gbc_clientTable.gridy = 0;
        panel.add(clientTableS, gbc_clientTable);

        this.clientTable = new JTable();
        clientTableS.setViewportView(this.clientTable);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(319, 73, 276, 149);
        this.getContentPane().add(panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        productTableS = new JScrollPane();
        GridBagConstraints gbc_productTable = new GridBagConstraints();
        gbc_productTable.fill = GridBagConstraints.BOTH;
        gbc_productTable.gridx = 0;
        gbc_productTable.gridy = 0;
        panel_1.add(productTableS, gbc_productTable);

        this.productTable = new JTable();
        productTableS.setViewportView(this.productTable);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(319, 246, 276, 149);
        this.getContentPane().add(panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{0, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0};
        gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        orderTableS = new JScrollPane();
        GridBagConstraints gbc_odrerTable = new GridBagConstraints();
        gbc_odrerTable.fill = GridBagConstraints.BOTH;
        gbc_odrerTable.gridx = 0;
        gbc_odrerTable.gridy = 0;
        panel_2.add(orderTableS, gbc_odrerTable);

        orderTable = new JTable();
        orderTableS.setViewportView(orderTable);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantity.setBounds(33, 286, 69, 20);
        this.getContentPane().add(lblQuantity);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldQuantity.setBounds(112, 286, 162, 20);
        this.getContentPane().add(textFieldQuantity);
        textFieldQuantity.setColumns(10);

        btnCreate = new JButton("Create");
        btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCreate.setBounds(104, 344, 114, 30);
        this.getContentPane().add(btnCreate);
    }

    /**
     * creates a listener for the 'Create' button
     * @param action
     */
    public void addCreateListener(ActionListener action){
        btnCreate.addActionListener(action);
    }

    /**
     * getter for quantity field
     * @return quantity
     */
    public String getFieldQuantity(){
        return textFieldQuantity.getText();
    }

    /**
     * setter for the client table in this view
     * @param clientTable
     */
    public void setClientTable(JTable clientTable){
        this.clientTable = clientTable;
    }

    /**
     * setter for the product table from this view
     * @param productTable
     */
    public void setProductTable(JTable productTable){
        this.productTable = productTable;
    }

    /**
     * setter for the order table from this view
     * @param orderTable
     */
    public void setOrderTable(JTable orderTable) {
        this.orderTable = orderTable;
    }

    /**
     * getter for the client table from this view
     * @return
     */
    public JTable getClientTable() {
        return clientTable;
    }

    /**
     * getter for the product table from this view
     * @return
     */
    public JTable getProductTable() {
        return productTable;
    }

    /**
     * checks if a row each from the client table and product table is selected
     * @return true if one row each is selected, otherwise false
     */
    public boolean isSelected(){
        if (productTable.getSelectedRow() >= 0 && clientTable.getSelectedRow() >= 0)
            return true;
        return false;
    }

    /**
     * shows the client table on the view
     * @param clientTable
     */
    public void showClientTable(JTable clientTable) {
        clientTableS.setViewportView(clientTable);
    }

    /**
     * shows the product table on the view
     * @param productTable
     */
    public void showProductTable(JTable productTable) {
        productTableS.setViewportView(productTable);
    }

    /**
     * shows the order table on the view
     * @param orderTable
     */
    public void showOrderTable(JTable orderTable) {
        orderTableS.setViewportView(orderTable);
    }

    /**
     * shows a pop-ip with a message
     * @param message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
