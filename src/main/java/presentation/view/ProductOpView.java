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

public class ProductOpView extends JFrame {

    private JTable productTable;
    private JButton btnAddProduct;
    private JButton btnEditProduct;
    private JButton btnDeleteProduct;
    private JButton btnViewAllProducts;
    private JScrollPane tableProductS;

    /**
     * creates the view where operations on the products table can be made
     */
    public ProductOpView() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblProductOp = new JLabel("Product Operations");
        lblProductOp.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblProductOp.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductOp.setBounds(231, 22, 162, 30);
        this.getContentPane().add(lblProductOp);

        btnAddProduct = new JButton("Add Product");
        btnAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddProduct.setBounds(66, 106, 119, 30);
        this.getContentPane().add(btnAddProduct);

        btnEditProduct = new JButton("Edit Product");
        btnEditProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEditProduct.setBounds(66, 174, 119, 30);
        this.getContentPane().add(btnEditProduct);

        btnDeleteProduct = new JButton("Delete Product");
        btnDeleteProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDeleteProduct.setBounds(66, 242, 119, 30);
        this.getContentPane().add(btnDeleteProduct);

        btnViewAllProducts = new JButton("View All");
        btnViewAllProducts.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnViewAllProducts.setBounds(66, 312, 119, 30);
        this.getContentPane().add(btnViewAllProducts);

        JPanel panel = new JPanel();
        panel.setBounds(280, 106, 304, 236);
        this.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        tableProductS = new JScrollPane();
        GridBagConstraints gbc_tableProduct = new GridBagConstraints();
        gbc_tableProduct.fill = GridBagConstraints.BOTH;
        gbc_tableProduct.gridx = 0;
        gbc_tableProduct.gridy = 0;
        panel.add(tableProductS, gbc_tableProduct);

        productTable = new JTable();
        tableProductS.setViewportView(productTable);
    }

    /**
     * creates a listener for the 'Add Product' button
     * @param action
     */
    public void addProductListener(ActionListener action){
        this.btnAddProduct.addActionListener(action);
    }

    /**
     * creates a listener for the 'Edit Product' button
     * @param action
     */
    public void editProductListener(ActionListener action){
        this.btnEditProduct.addActionListener(action);
    }

    /**
     * creates a listener for the 'Delete Product' button
     * @param action
     */
    public void deleteProductListener(ActionListener action){
        this.btnDeleteProduct.addActionListener(action);
    }

    /**
     * creates a listener for the 'View All' button
     * @param action
     */
    public void viewAllProductsListener(ActionListener action){
        this.btnViewAllProducts.addActionListener(action);
    }

    /**
     * shows the product table on the view
     * @param productTable
     */
    public void showProductTable(JTable productTable) {
        tableProductS.setViewportView(productTable);
    }

    /**
     * setter for the product table of this view
     * @param table
     */
    public void setProductTable(JTable table){
        this.productTable = table;
    }

    /**
     * getter for the product table of this view
     * @return product table
     */
    public JTable getProductTable() {
        return productTable;
    }

    /**
     * checks if a row from the product table is selected
     * @return true if a row is selected or false if none are selected
     */
    public boolean isSelected(){
        if (productTable.getSelectedRow() >= 0)
            return true;
        return false;
    }

    /**
     * shows a pop-up with a message
     * @param message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
