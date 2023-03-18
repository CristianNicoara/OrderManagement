package dataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dataAccessLayer.connection.ConnectionFactory;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 21, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 * @Source: https://gitlab.com/utcn_dsrl/pt-reflection-example
 */


public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * creates the select query
     * @param field
     * @return the query
     */
    private String createSelectQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("`" + type.getSimpleName() + "`");
        sb.append(" WHERE " + field + " =?");

        return sb.toString();
    }

    /**
     * creates the insert into table query based on the table
     * @return the query
     */
    private String createInsertQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT");
        sb.append(" INTO ");
        sb.append("`" + type.getSimpleName() + "`");
        if (type.getSimpleName().equals("Client")) {
            sb.append(" (name,address,email) ");
            sb.append(" VALUES (?,?,?)");
        } else if (type.getSimpleName().equals("Product")) {
            sb.append(" (name,stock) ");
            sb.append(" VALUES (?,?)");
        } else {
            sb.append(" (clientId,productId,clientName,clientAddress,productName,orderSize) ");
            sb.append(" VALUES (?,?,?,?,?,?)");
        }

        return sb.toString();
    }

    /**
     * creates the update table query based on the table
     * @return the query
     */
    private String createUpdateQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        if (type.getSimpleName().equals("Client")) {
            sb.append(" name = ?, address = ?, email = ? ");
            sb.append("WHERE id = ?");
        } else if (type.getSimpleName().equals("Product")) {
            sb.append(" name = ?, stock = ? ");
            sb.append(" WHERE id = ?");
        }

        return sb.toString();
    }

    /**
     * creates the delete from table query
     * @param field
     * @return the query
     */
    private String createDeleteQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
    }

    /**
     * executes the select query from a table based on the id
     * @param id
     * @return the table element selected or null if there was an exception encountered
     */
    public T findById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * executes the insert into table query depending on the instance of the parameter received
     * @param t
     * @return the element inserted or null if an exception was encountered
     */
    public T insert(T t){
        Connection connection = null;
        PreparedStatement insertStatement = null;
        String query = createInsertQuery();

        try{
            connection = ConnectionFactory.getConnection();
            insertStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (t instanceof Client){
                //insertStatement.setInt(1,((Client) t).getId());
                insertStatement.setString(1,((Client) t).getName());
                insertStatement.setString(2,((Client) t).getAddress());
                insertStatement.setString(3,((Client) t).getEmail());
            } else if (t instanceof Product){
                //insertStatement.setInt(1, ((Product) t).getId());
                insertStatement.setString(1, ((Product) t).getName());
                insertStatement.setInt(2, ((Product) t).getStock());
            } else if (t instanceof Order){
               // insertStatement.setInt(1, ((Order) t).getId());
                insertStatement.setInt(1, ((Order) t).getClientId());
                insertStatement.setInt(2, ((Order) t).getProductId());
                insertStatement.setString(3,((Order) t).getClientName());
                insertStatement.setString(4,((Order) t).getClientAddress());
                insertStatement.setString(5,((Order) t).getProductName());
                insertStatement.setInt(6,((Order) t).getOrderSize());
            }
            insertStatement.executeUpdate();

            return t;
            //ResultSet rs = insertStatement.getGeneratedKeys();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "DAO:insert " + e.getMessage());
        }finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * executed the update table query depending on the instance of the parameter received
     * @param t
     * @return the element updated or null if an exception was encountered
     */
    public T update(T t){
        Connection connection = null;
        PreparedStatement updateStatement = null;
        String query = createUpdateQuery();

        try{
            connection = ConnectionFactory.getConnection();
            updateStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (t instanceof Client){
                updateStatement.setString(1,((Client) t).getName());
                updateStatement.setString(2,((Client) t).getAddress());
                updateStatement.setString(3,((Client) t).getEmail());
                updateStatement.setInt(4,((Client) t).getId());
            } else if (t instanceof Product){
                updateStatement.setString(1, ((Product) t).getName());
                updateStatement.setInt(2, ((Product) t).getStock());
                updateStatement.setInt(3, ((Product) t).getId());
            }
            updateStatement.executeUpdate();

            return t;
            //ResultSet rs = updateStatement.getGeneratedKeys();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "DAO:update " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * executes the delete query based on the id received
     * @param id
     */
    public void delete(int id){
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        String query = createDeleteQuery("id");
         try{
             connection = ConnectionFactory.getConnection();
             deleteStatement = connection.prepareStatement(query);
             deleteStatement.setInt(1, id);
             deleteStatement.execute();
         } catch (SQLException e){
             LOGGER.log(Level.WARNING, "DAO:delete " + e.getMessage());
         } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
         }
    }

    /**
     * executes the select all query
     * @return a list of objects converted from the result of the query or null if an exception was found
     */
    public List<T> findAll(){
        List<T> res;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT");
        query.append("* ");
        query.append("FROM ");
        query.append("`" + type.getSimpleName() + "`");

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();

            res = createObjects(resultSet);
            return res;
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *converts the result set of a query into a list of objects
     * @param resultSet
     * @return the list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
