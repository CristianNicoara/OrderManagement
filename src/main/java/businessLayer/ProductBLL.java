package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Client;
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


public class ProductBLL {
    private ProductDAO productDAO;

    /**
     * The constructor of this class which creates a new instance of ProductDAO
     */
    public ProductBLL(){
        productDAO = new ProductDAO();
    }

    /**
     * calls the findById method from ProductDAO
     * @param id
     * @return the product if it was found else null
     */
    public Product findProductById(int id){
        Product product = productDAO.findById(id);
        if (product == null){
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return product;
    }

    /**
     * calls the insert method from ProductDAO
     * @param product
     */
    public void insertProduct(Product product){
        productDAO.insert(product);
    }

    /**
     * calls the update method from ProductDAO
     * @param product
     */
    public void updateProduct(Product product){
        productDAO.update(product);
    }

    /**
     * calls the delete method from ProductDAO
     * @param id
     */
    public void deleteProduct(int id){
        productDAO.delete(id);
    }

    /**
     * calls the findAll method from ProductDAO
     * @return a list of the products from the database table
     */
    public List<Product> findAll(){
        List<Product> productList = productDAO.findAll();

        return productList;
    }
}
