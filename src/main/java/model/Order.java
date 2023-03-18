package model;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 16, 2022
 */

public class Order {

    private int id;
    private int clientId;
    private int productId;
    private String clientName;
    private String clientAddress;
    private String productName;
    private int orderSize;

    public Order(){

    }

    public Order(int id, int clientId, int productId, String clientName, String clientAddress, String productName, int orderSize) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.productName = productName;
        this.orderSize = orderSize;
    }

    public Order(int clientId, int productId, String clientName, String clientAddress, String productName, int orderSize) {
        super();
        this.clientId = clientId;
        this.productId = productId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.productName = productName;
        this.orderSize = orderSize;
    }

    /**
     * getter for order id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for order id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for client id from order table
     * @return idClinet
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * setter for client id from order table
     * @param clientId
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * getter for product id from order table
     * @return productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * setter for product id from order table
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * getter for client name from order table
     * @return clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * setter for client name from order table
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * getter for client address from order table
     * @return clientAddress
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * setter for client address from order table
     * @param clientAddress
     */
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * getter for product name from order table
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setter for product name from order table
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * getter for order size
     * @return orderSize
     */
    public int getOrderSize() {
        return orderSize;
    }

    /**
     * setter for order size
     * @param orderSize
     */
    public void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }
}
