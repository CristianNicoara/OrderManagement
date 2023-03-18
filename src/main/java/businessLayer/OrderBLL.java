package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 21, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 * @Source: https://gitlab.com/utcn_dsrl/pt-reflection-example
 */


public class OrderBLL {
    private OrderDAO orderDAO;

    /**
     * The constructor of this class which creates a new instance of OrderDAO
     */
    public OrderBLL(){
        orderDAO = new OrderDAO();
    }

    /**
     * calls the method findById from OrderDAO
     * @param id
     * @return null if there was no order found with the id given else the order found
     */
    public Order findOrderById(int id){
        Order order = orderDAO.findById(id);
        if (order == null){
            throw new NoSuchElementException("The order with id = " + id + " was not found!");
        }
        return order;
    }

    /**
     * calls the method insert from OrderDAO
     * @param order
     */
    public void createOrder(Order order){
        orderDAO.insert(order);
    }

    /**
     * calls the method findAll from OrderDAO
     * @return a list of orders from the database table
     */
    public List<Order> findAll(){
        List<Order> orderList = orderDAO.findAll();

        return orderList;
    }
}
