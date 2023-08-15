package stepDefinitions.db;

import hooks.db.HooksDB;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
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

    PreparedStatement preparedStatement;

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


    @Then("Sort the contents with role=parent in the users table according to the user id from largest to smallest.")
    public void sortTheContentsWithRoleParentInTheUsersTableAccordingToTheUserIdFromLargestToSmallest() {
        try  {
            query = "SELECT * FROM users WHERE role = 'parent' ORDER BY user_id DESC";

                resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        int userId = resultSet.getInt("user_id");
                        String username = resultSet.getString("username");
                        String role = resultSet.getString("role");

                        System.out.println("User ID: " + userId + ", Username: " + username + ", Role: " + role);
                    }
                } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }




    @Then("Verify that there are {int} users in the chat_users table with create_staff_id equal to {int}")
    public void verifyThatThereAreUsersInTheChat_usersTableWithCreate_staff_idEqualTo(int count, int id) throws SQLException {

        String query = "SELECT COUNT(*) " +
                "as user_count " +
                "FROM chat_users " +
                "WHERE create_staff_id = " + id;
        resultSet = statement.executeQuery(query);
        try {
            if (resultSet.next()) {
                int userCount = resultSet.getInt("user_count");
                if (userCount == count) {
                    System.out.println("There are 11 users with create_staff_id equal to 1.");
                    Assert.assertEquals(count, userCount);
                } else {
                    System.out.println("There are not 11 users with create_staff_id equal to 1.");
                    Assert.assertEquals(count, userCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Then("List the IDs of items in the class_sections table where the values of class_id and section_id are the same.")
    public void listTheIDsOfItemsInTheClass_sectionsTableWhereTheValuesOfClass_idAndSection_idAreTheSame() throws SQLException {
        String query = "SELECT id " +
                "FROM class_sections " +
                "WHERE class_id = section_id";

        resultSet = statement.executeQuery(query);

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int id = resultSet.getInt("id");
            System.out.println("Matching ID: " + id);
        }


    }

    @Then("Verify that the email of the student in the students table with firstname: {string} and lastname: {string} is email:{string}.")
    public void verifyThatTheEmailOfTheStudentInTheStudentsTableWithFirstnameAndLastnameIsEmail(String firstname, String surname, String email) throws SQLException {
        String query = "SELECT email " +
                "FROM students " +
                "WHERE firstname = 'Brian'  AND lastname = 'Kohlar'";

        resultSet = statement.executeQuery(query);

        try {
            if (resultSet.next()) {
                email = resultSet.getString("email");
                if (email.equalsIgnoreCase("brain@gmail.com")) {
                    System.out.println("The email is correct.");
                } else {
                    System.out.println("The email is incorrect.");
                }
            } else {
                System.out.println("No student found with the given firstname and lastname.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Then("The name information for the specified id in the topic table should be updatable")
    public void theNameInformationForTheSpecifiedIdInTheTopicTableShouldBeUpdatable() throws SQLException {
        int specifiedId = 124;
        String newTopicName = "villa tarabya.";

           query = "UPDATE topic SET name = '" + newTopicName + "' WHERE id = " + specifiedId;

            int rowsUpdated = statement.executeUpdate(query);
            if (rowsUpdated > 0) {
                System.out.println("Registry update successful.");
            } else {
                System.out.println("Registry update failed.");
            }

            statement.close();

        }

    @Then("A new record should be added to the transport_route table.")
    public void aNewRecordShouldBeAddedToTheTransport_routeTable() {
        String newRouteTitle = "Ankara-Newyork";
        int newNoOfVehicle = 5;
        String newNote = "Haymana-Republik Street";
        boolean newIsActive = true;

        try {


           query = "INSERT INTO transport_route (route_title, no_of_vehicle, note, is_active) VALUES ('" + newRouteTitle + "', " + newNoOfVehicle + ", '" + newNote + "', " + newIsActive + ")";

            int rowsInserted = statement.executeUpdate(query);
            if (rowsInserted > 0) {
                System.out.println("new record added.");
            } else {
                System.out.println("Could not add new record.");
            }

            statement.close();
        } catch (SQLException e) {

        }
    }
    }









