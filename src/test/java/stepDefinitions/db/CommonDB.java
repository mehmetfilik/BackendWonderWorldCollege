package stepDefinitions.db;

import hooks.db.HooksDB;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ConfigReader;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CommonDB {

    String query;
    ResultSet resultSet;
    Connection connection;
    Statement statement;

    @Given("Start Communication With WonderWorldCollege DataBase")
    public void start_communication_with_wonder_world_college_data_base() throws SQLException {
        connection = DriverManager.getConnection(ConfigReader.getProperty("dbUrl"),ConfigReader.getProperty("dbUsername"),ConfigReader.getProperty("dbPssword"));
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    }
    @Then("List the last {int} records from the online_admissions table")
    public void list_the_last_records_from_the_online_admissions_table(Integer number) throws SQLException {
        query = "SELECT * " +
                "FROM online_admissions " +
                "ORDER BY created_at DESC " +
                "LIMIT "+number;
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){

            int columnCount = resultSet.getMetaData().getColumnCount();

            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                System.out.print(resultSet.getString(columnIndex) + "\t");
            }
            System.out.println();
        }

    }
    @When("Close the DataBase")
    public void close_the_data_base() throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();
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
}
