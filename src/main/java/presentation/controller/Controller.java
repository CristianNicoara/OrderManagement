package presentation.controller;

import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;
import model.Client;
import model.Order;
import model.Product;
import presentation.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: May 04, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 * @Source: https://gitlab.com/utcn_dsrl/pt-reflection-example
 */

public class Controller {
    private ClientOpView clientOpView;
    private ProductOpView productOpView;
    private AddClientView addClientView;
    private AddProductView addProductView;
    private CreateOrderView createOrderView;
    private EditProductView editProductView;
    private EditClientView editClientView;
    private ViewSel viewSel;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private JTable table;
    private FileWriter fileWriter;

    /**
     * contructor for the controller where an instance of each interface is created
     * @param clientOpView
     * @param productOpView
     * @param addClientView
     * @param addProductView
     * @param createOrderView
     * @param viewSel
     * @param editClientView
     * @param editProductView
     */
    public Controller(ClientOpView clientOpView, ProductOpView productOpView, AddClientView addClientView, AddProductView addProductView, CreateOrderView createOrderView, ViewSel viewSel, EditClientView editClientView, EditProductView editProductView) {
        this.clientOpView = clientOpView;
        this.productOpView = productOpView;
        this.addClientView = addClientView;
        this.addProductView = addProductView;
        this.editClientView = editClientView;
        this.editProductView = editProductView;
        this.createOrderView = createOrderView;
        this.viewSel = viewSel;
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();

        this.viewSel.addClientInterfaceListener(new ClientInterface());
        this.viewSel.addProductInterfaceListener(new ProductInterface());
        this.viewSel.addOrderInterfaceListener(new OrderInterface());
    }

    /**
     * gets the id of a selected row in a table
     * @param table
     * @return id
     */
    public int getIdFromRowNumber(JTable table) {
        int id;
        String data = null;
        int column = 0;
        int row = table.getSelectedRow();
        data = (String) table.getValueAt(row, column);
        id = Integer.parseInt(data);

        return id;
    }

    /**
     * creates a database table of the list of objects received
     * @param objects
     * @return the created table
     */
    public JTable generateTable(Object[] objects){
        ArrayList<String> columns = new ArrayList<String>();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (Field field : objects[0].getClass().getDeclaredFields()){
            field.setAccessible(true);
            columns.add(field.getName());
        }
        for (int j = 0; j < objects.length; j++){
            data.add(new ArrayList<>());
            for (Field field : objects[j].getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(objects[j]);
                    data.get(j).add(value.toString());
                } catch (IllegalArgumentException e){
                    e.printStackTrace();
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        String columns1[] = new String[columns.size()];
        for (int i = 0; i < columns.size(); i++){
            columns1[i] = columns.get(i);
        }
        String data1[][] = new String[data.size()][data.get(0).size()];
        for (int i = 0; i < data.size(); i++){
            for (int j = 0; j < data.get(i).size(); j++){
                data1[i][j] = data.get(i).get(j);
            }
        }
        JTable table = new JTable(data1,columns1);

        return table;
    }

    class ClientInterface implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productOpView.setVisible(false);
            createOrderView.setVisible(false);
            addProductView.setVisible(false);
            editProductView.setVisible(false);
            clientOpView.setVisible(true);
            clientOpView.addClientListener(new AddClient());
            clientOpView.editClientListener(new EditClient());
            clientOpView.deleteClientListener(new DeleteClient());
            clientOpView.viewAllClientsListener(new ViewAllClients());

        }
    }

    class ProductInterface implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientOpView.setVisible(false);
            createOrderView.setVisible(false);
            addClientView.setVisible(false);
            editClientView.setVisible(false);
            productOpView.setVisible(true);
            productOpView.addProductListener(new AddProduct());
            productOpView.editProductListener(new EditProduct());
            productOpView.deleteProductListener(new DeleteProduct());
            productOpView.viewAllProductsListener(new ViewAllProducts());

        }
    }

    class OrderInterface implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productOpView.setVisible(false);
            clientOpView.setVisible(false);
            addClientView.setVisible(false);
            editClientView.setVisible(false);
            addProductView.setVisible(false);
            editProductView.setVisible(false);
            createOrderView.setVisible(true);

            List<Client> clients = clientBLL.findAll();
            Client[] clients1 = new Client[clients.size()];
            clients1 = clients.toArray(clients1);
            table = generateTable(clients1);
            createOrderView.showClientTable(table);
            createOrderView.setClientTable(table);

            List<Product> products = productBLL.findAll();
            Product[] products1 = new Product[products.size()];
            products1 = products.toArray(products1);
            table = generateTable(products1);
            createOrderView.showProductTable(table);
            createOrderView.setProductTable(table);

            createOrderView.addCreateListener(new CreateOrder());

        }
    }

    class AddClient implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productOpView.setVisible(false);
            createOrderView.setVisible(false);
            addClientView.refresh();
            addClientView.setVisible(true);
            editClientView.setVisible(false);
            addProductView.setVisible(false);
            editProductView.setVisible(false);
            addClientView.addBackListener(e1 -> {
                String name = addClientView.getNameField();
                String address = addClientView.getAddressField();
                String email = addClientView.getEmailField();
                if (name != null && address != null && email != null)
                    clientBLL.insertClient(new Client(name, address, email));
                else
                    addClientView.showMessage("Don't leave any text field empty");
                addClientView.setVisible(false);
            });
        }
    }

    class EditClient implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            productOpView.setVisible(false);
            createOrderView.setVisible(false);
            addProductView.setVisible(false);
            editProductView.setVisible(false);
            addClientView.setVisible(false);
            editClientView.refresh();
            clientOpView.setClientTable(table);
            if (clientOpView.isSelected()) {
                editClientView.setVisible(true);
                id = getIdFromRowNumber(clientOpView.getClientTable());
                editClientView.addBackListener(e1 -> {
                    String name = editClientView.getNameField();
                    String address = editClientView.getAddressField();
                    String email = editClientView.getEmailField();
                    if (name != null && address != null && email != null)
                        clientBLL.updateClient(new Client(id, name, address, email));
                    else
                        editClientView.showMessage("Don't leave any text field empty");
                    editClientView.setVisible(false);
                });
            } else
                clientOpView.showMessage("Choose a row");
        }
    }

    class DeleteClient implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            clientOpView.setClientTable(table);
            if (clientOpView.isSelected()){
                id = getIdFromRowNumber(clientOpView.getClientTable());
                clientBLL.deleteClient(id);
            } else
                clientOpView.showMessage("Choose a row");
        }
    }

    class ViewAllClients implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Client> clients = clientBLL.findAll();
            Client[] clients1 = new Client[clients.size()];
            clients1 = clients.toArray(clients1);
            table = generateTable(clients1);
            clientOpView.showClientTable(table);
        }
    }

    class AddProduct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientOpView.setVisible(false);
            createOrderView.setVisible(false);
            addClientView.setVisible(false);
            editClientView.setVisible(false);
            editProductView.setVisible(false);
            addProductView.refresh();
            addProductView.setVisible(true);
            addProductView.addBackListener(e1 -> {
                String name = addProductView.getNameField();
                String stockS = addProductView.getStockField();
                if (name != null && stockS != null) {
                    int stock = Integer.parseInt(stockS);
                    productBLL.insertProduct(new Product(name, stock));
                }
                else
                    addProductView.showMessage("Don't leave any text field empty");
                addProductView.setVisible(false);
            });
        }
    }

    class EditProduct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientOpView.setVisible(false);
            createOrderView.setVisible(false);
            addClientView.setVisible(false);
            editClientView.setVisible(false);
            addProductView.setVisible(false);
            int id;
            editProductView.refresh();
            productOpView.setProductTable(table);
            if (productOpView.isSelected()) {
                editProductView.setVisible(true);
                id = getIdFromRowNumber(productOpView.getProductTable());
                editProductView.addBackListener(e1 -> {
                    String name = editProductView.getNameField();
                    String stockS = editProductView.getStockField();
                    if (name != null && stockS != null) {
                        int stock = Integer.parseInt(stockS);
                        productBLL.updateProduct(new Product(id, name, stock));
                    }
                    else
                        editProductView.showMessage("Don't leave any text field empty");
                    editProductView.setVisible(false);
                });
            } else
                productOpView.showMessage("Choose a row");
        }
    }

    class DeleteProduct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            productOpView.setProductTable(table);
            if (productOpView.isSelected()){
                id = getIdFromRowNumber(productOpView.getProductTable());
                productBLL.deleteProduct(id);
            } else
                productOpView.showMessage("Choose a row");
        }
    }

    class ViewAllProducts implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Product> products = productBLL.findAll();
            Product[] products1 = new Product[products.size()];
            products1 = products.toArray(products1);
            table = generateTable(products1);
            productOpView.showProductTable(table);
        }
    }

    class CreateOrder implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String orderSizeS = createOrderView.getFieldQuantity();
            if (!orderSizeS.equals("")){
                int orderSize = Integer.parseInt(orderSizeS);
                if (orderSize > 0){
                    if (createOrderView.isSelected()){
                        int idClient = getIdFromRowNumber(createOrderView.getClientTable());
                        int idProduct = getIdFromRowNumber(createOrderView.getProductTable());
                        Client client = clientBLL.findClientById(idClient);
                        Product product = productBLL.findProductById(idProduct);
                        if (orderSize < product.getStock()) {
                            int nr = 1;
                            String clientName = client.getName();
                            String clientAddress = client.getAddress();
                            String productName = product.getName();
                            orderBLL.createOrder(new Order(idClient, idProduct, clientName, clientAddress, productName, orderSize));

                            List<Order> orders = orderBLL.findAll();
                            Order[] orders1 = new Order[orders.size()];
                            orders1 = orders.toArray(orders1);
                            table = generateTable(orders1);
                            createOrderView.showOrderTable(table);

                            productBLL.updateProduct(new Product(product.getId(), product.getName(), (product.getStock() - orderSize)));
                            List<Product> products = productBLL.findAll();
                            Product[] products1 = new Product[products.size()];
                            products1 = products.toArray(products1);
                            table = generateTable(products1);
                            createOrderView.showProductTable(table);

                            String txt = clientName + " from " + clientAddress + " ordered " + orderSize + " kg of " + productName + "\n";
                            String filename = "bill_" + clientName + nr + ".txt";
                            try {
                                fileWriter = new FileWriter(filename, true);
                                fileWriter.write(txt);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                fileWriter.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            nr++;
                        } else
                            createOrderView.showMessage("Under-stock!");
                    } else
                        createOrderView.showMessage("Select a client and a product!");
                } else
                    createOrderView.showMessage("Write a positive order quantity!");
            } else
                createOrderView.showMessage("Write a quantity!");
        }
    }
}
