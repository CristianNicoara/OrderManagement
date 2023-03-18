package model;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 16, 2022
 */

public class Client {

    private int id;
    private String name;
    private String address;
    private String email;

    public Client() {
    }

    public Client(int id, String name, String address, String email) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Client(String name, String address, String email) {
        super();
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * getter for client id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for client id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for client name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for client name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for client address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter for client address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter for client email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for client email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
