package utilities;

import java.sql.*;

public class DB_Utils {

    private static ResultSet resultSet;
    private static Connection connection;
    private static Statement statement;

    public static void createConnection(){
        try {
            connection = DriverManager.getConnection(ConfigReader.getProperty("dbUrl"),ConfigReader.getProperty("dbUsername"),ConfigReader.getProperty("dbPssword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void executeQuery(String query){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection(){
        try {
        if (resultSet != null){
            resultSet.close();
        }
        if (statement != null){
            statement.close();
        }
        if (connection != null){
            connection.close();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Statement getStatement(){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(ConfigReader.getProperty("dbUrl"),ConfigReader.getProperty("dbUsername"),ConfigReader.getProperty("dbPssword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static ResultSet getResultSet(){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
