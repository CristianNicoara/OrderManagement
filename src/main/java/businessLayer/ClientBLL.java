package businessLayer;

import businessLayer.validators.EmailValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 21, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 * @Source: https://gitlab.com/utcn_dsrl/pt-reflection-example
 */

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * The constructor of this class which creates a new instance of ClientDAO and a list of validators
     */
    public ClientBLL(){
        validators = new ArrayList<>();
        validators.add(new EmailValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * calls the method findById from ClientDAO
     * @param id
     * @return null if there was no client found else the client
     */
    public Client findClientById(int id){
        Client client = clientDAO.findById(id);
        if (client == null){
            throw new NoSuchElementException("The client with id = " + id + " was not found!");
        }
        return client;
    }

    /**
     * calls the insert method from clientDAO
     * @param client
     */
    public void insertClient(Client client){
        clientDAO.insert(client);
    }

    /**
     * calls the update method from clientDAO
     * @param client
     */
    public void updateClient(Client client){
        clientDAO.update(client);
    }

    /**
     * calls the delete method from clientDAO
     * @param id
     */
    public void deleteClient(int id){
        clientDAO.delete(id);
    }

    /**
     * calls the findAll method from clientDAO
     * @return a list of clients from the database table
     */
    public List<Client> findAll(){
        List<Client> clientList = clientDAO.findAll();

        return clientList;
    }
}
