package stepDefinitions.db;

import hooks.db.HooksDB;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ConfigReader;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static utilities.DB_Utils.*;

public class CommonDB {

    String query;
    ResultSet resultSet;
    Connection connection;
    Statement statement;

    @Given("Start Communication With WonderWorldCollege DataBase")
    public void start_communication_with_wonder_world_college_data_base() {
        //      connection = DriverManager.getConnection(ConfigReader.getProperty("dbUrl"),ConfigReader.getProperty("dbUsername"),ConfigReader.getProperty("dbPssword"));
        //      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        createConnection();
        statement = getStatement();
    }

    @Then("List the last {int} records from the online_admissions table")
    public void list_the_last_records_from_the_online_admissions_table(Integer number) throws SQLException {
        query = "SELECT * " +
                "FROM online_admissions " +
                "ORDER BY created_at DESC " +
                "LIMIT " + number;
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                System.out.print(resultSet.getString(columnIndex) + "\t");
            }
            System.out.println();
        }

    }

    @When("Close the DataBase")
    public void close_the_data_base() {
        //      connection.close();
        //      statement.close();
        //      resultSet.close();
        closeConnection();
    }


    @Then("Passing Percentage Average")
    public void passingPercentageAverage() throws SQLException {
        String query = "SELECT AVG(passing_percentage) AS avg_passing_percentage FROM onlineexam";

        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            double averagePassingPercentage = resultSet.getDouble("avg_passing_percentage");
            System.out.println("Average Passing Percentage: " + averagePassingPercentage);
        }
    }

    @Then("Unique Student Count on onlineexam_students")
    public void uniqueStudentCountOnOnlineexam_students() throws SQLException {
        String query = "SELECT DISTINCT student_session_id FROM onlineexam_students";

        resultSet = statement.executeQuery(query);

        Set<String> uniqueStudentSessionIds = new HashSet<>();

        while (resultSet.next()) {
            String studentSessionId = resultSet.getString("student_session_id");
            uniqueStudentSessionIds.add(studentSessionId);
        }

        int uniqueStudentCount = uniqueStudentSessionIds.size();
        System.out.println("Number of Unique Students: " + uniqueStudentCount);

    }


    @Then("List the email addresses of records in the online_admissions table where the firstname contains the word {string}")
    public void listTheEmailAddressesOfRecordsInTheOnline_admissionsTableWhereTheFirstnameContainsTheWord(String al) throws SQLException {

        String query = "SELECT email " +
                "FROM online_admissions " +
                "WHERE firstname LIKE '%al%'";
        resultSet = statement.executeQuery(query);

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String email = null;
            try {
                email = resultSet.getString("email");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Email: " + email);
        }
    }

    @Then("List the book titles of books in the books table where the author data is {string} or {string}.")
    public void listTheBookTitlesOfBooksInTheBooksTableWhereTheAuthorDataIsOr(String RubinaMalik, String MienAli) throws SQLException {

        String query = "SELECT book_title " +
                "FROM books " +
                "WHERE author = 'Rubina malik' OR author = 'Mien  Ali'";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String title = resultSet.getString("book_title");
            System.out.println("Book Title: " + title);
        }
    }


    @Then("List the books from the books table where the quantity qty value is between {string} and {string}.")
    public void listTheBooksFromTheBooksTableWhereTheQuantityQtyValueIsBetweenAnd(String arg0, String arg1) throws SQLException {

        String query = "SELECT * " +
                "FROM books " +
                "WHERE qty " +
                "BETWEEN 100 AND 500;";
        resultSet = statement.executeQuery(query);

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String title = null;
            try {
                title = resultSet.getString("book_title");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String author = null;
            try {
                author = resultSet.getString("author");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int quantity = 0;
            try {
                quantity = resultSet.getInt("qty");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("book_title: " + title + ", Author: " + author + ", Qty: " + quantity);
        }

    }
}