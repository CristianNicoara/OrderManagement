package dataAccessLayer.connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 16, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 */

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehousedb";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates a connection with the database
     * @return the connection
     */
    private Connection createConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
        }
        return connection;
    }

    /**
     * calls the createConnection from this class, because it's private
     * @return the connection
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * closes the connection with the database
     * @param connection
     */
    public static void close(Connection connection){
        if (connection != null){
            try{
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * closes the statement
     * @param statement
     */
    public static void close(Statement statement){
        if (statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * closes the result set
     * @param resultSet
     */
    public static void close(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }

}
