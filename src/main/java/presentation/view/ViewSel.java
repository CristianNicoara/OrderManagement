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

public class ViewSel extends JFrame {

    private JButton btnOrderInterface;
    private JButton btnClientInterface;
    private JButton btnProductInterface;

    /**
     * creates the view where the table on which the user wants to do queries can be selected
     */
    public ViewSel(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblInterfaceSelection = new JLabel("Interface Selection");
        lblInterfaceSelection.setHorizontalAlignment(SwingConstants.CENTER);
        lblInterfaceSelection.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblInterfaceSelection.setBounds(223, 33, 177, 30);
        this.getContentPane().add(lblInterfaceSelection);

        JLabel lblNewLabel = new JLabel("Choose the interface you want to open!");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(165, 91, 293, 30);
        this.getContentPane().add(lblNewLabel);

        btnClientInterface = new JButton("Client Interface");
        btnClientInterface.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnClientInterface.setBounds(242, 161, 140, 30);
        this.getContentPane().add(btnClientInterface);

        btnProductInterface = new JButton("Product Interface");
        btnProductInterface.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnProductInterface.setBounds(242, 226, 140, 30);
        this.getContentPane().add(btnProductInterface);

        btnOrderInterface = new JButton("Order Interface");
        btnOrderInterface.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnOrderInterface.setBounds(242, 296, 140, 30);
        this.getContentPane().add(btnOrderInterface);

        this.setVisible(true);
    }

    /**
     * creates the listener for the 'Client Interface' button
     * @param action
     */
    public void addClientInterfaceListener(ActionListener action){
        btnClientInterface.addActionListener(action);
    }

    /**
     * creates the listener for the 'Product Interface' button
     * @param action
     */
    public void addProductInterfaceListener(ActionListener action){
        btnProductInterface.addActionListener(action);
    }

    /**
     * creates the listener for the 'Order Interface' button
     * @param action
     */
    public void addOrderInterfaceListener(ActionListener action){
        btnOrderInterface.addActionListener(action);
    }
}
