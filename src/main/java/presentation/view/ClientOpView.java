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

public class ClientOpView extends JFrame{

    private JTable clientTable;
    private JButton btnAddClient;
    private JButton btnEditClient;
    private JButton btnDeleteClient;
    private JButton btnViewAllClients;
    private JScrollPane clientTableS;

    /**
     * creates the view where operations on the clients table can be made
     */
    public  ClientOpView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblClientOp = new JLabel("Client Operations");
        lblClientOp.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblClientOp.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientOp.setBounds(231, 22, 162, 30);
        this.getContentPane().add(lblClientOp);

        btnAddClient = new JButton("Add Client");
        btnAddClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddClient.setBounds(74, 106, 111, 30);
        this.getContentPane().add(btnAddClient);

        btnEditClient = new JButton("Edit Client");
        btnEditClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEditClient.setBounds(74, 174, 111, 30);
        this.getContentPane().add(btnEditClient);

        btnDeleteClient = new JButton("Delete Client");
        btnDeleteClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDeleteClient.setBounds(74, 242, 111, 30);
        this.getContentPane().add(btnDeleteClient);

        btnViewAllClients = new JButton("View All");
        btnViewAllClients.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnViewAllClients.setBounds(74, 312, 111, 30);
        this.getContentPane().add(btnViewAllClients);

        JPanel panel = new JPanel();
        panel.setBounds(280, 106, 304, 236);
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

        clientTable = new JTable();
        clientTable.setRowSelectionAllowed(true);
        clientTable.setColumnSelectionAllowed(true);
        //clientTable.setCellSelectionEnabled(true);
        clientTableS.setViewportView(this.clientTable);
    }

    /**
     * creates a listener for the 'Add Client' button
     * @param action
     */
    public void addClientListener(ActionListener action){
        this.btnAddClient.addActionListener(action);
    }

    /**
     * creates a listener for the 'Edit Client' button
     * @param action
     */
    public void editClientListener(ActionListener action){
        this.btnEditClient.addActionListener(action);
    }

    /**
     * creates a listener for the 'Delete Client' button
     * @param action
     */
    public void deleteClientListener(ActionListener action){
        this.btnDeleteClient.addActionListener(action);
    }

    /**
     * creates a listener for the 'View All' button
     * @param action
     */
    public void viewAllClientsListener(ActionListener action){
        this.btnViewAllClients.addActionListener(action);
    }

    /**
     * shows on the view the clients table
     * @param clientTable
     */
    public void showClientTable(JTable clientTable) {
        clientTableS.setViewportView(clientTable);
    }

    /**
     * setter for client table
     * @param table
     */
    public void setClientTable(JTable table){
        this.clientTable = table;
    }

    /**
     * getter for client table
     * @return client table
     */
    public JTable getClientTable() {
        return clientTable;
    }

    /**
     * checks if a row is selected in the table
     * @return true if a row is selected or false if none are selected
     */
    public boolean isSelected(){
        if (clientTable.getSelectedRow() >= 0)
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
