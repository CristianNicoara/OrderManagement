package model;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 16, 2022
 */

public class Product {

    private int id;
    private String name;
    private int stock;

    public Product(){

    }

    public Product(int id, String name, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public Product(String name, int stock) {
        super();
        this.name = name;
        this.stock = stock;
    }

    /**
     * getter for product id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for product id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for product name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for product stock
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * setter for product stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
