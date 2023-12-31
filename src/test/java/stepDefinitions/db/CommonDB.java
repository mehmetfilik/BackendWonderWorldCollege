package stepDefinitions.db;

import hooks.db.HooksDB;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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


    @Then("Sort the contents with role=parent in the users table according to the user id from largest to smallest.")
    public void sortTheContentsWithRoleParentInTheUsersTableAccordingToTheUserIdFromLargestToSmallest() {
        try {
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

    //hamza
    @When("a query is executed to retrieve the {int} longest \\(text) values from the email column in the students table")
    public void aQueryIsExecutedToRetrieveTheLongestTextValuesFromTheEmailColumnInTheStudentsTable(int number) throws SQLException {
        List<String> longestEmails = new ArrayList<>();
        //Statement statement = connection.createStatement();

        String query = "SELECT email FROM students ORDER BY LENGTH(email) DESC LIMIT " + number;

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            longestEmails.add(email);
        }

        // resultSet.close();
        // statement.close();
        // connection.close();

        // Do something with longestEmails, such as printing or further processing
        for (String email : longestEmails) {
            System.out.println(email);
        }
    }

    @When("The top {int} individuals with the highest amount value are listed from the Income table")
    public void theTopIndividualsWithTheHighestAmountValueAreListedFromTheIncomeTable(int arg0) throws SQLException {
        query = "SELECT id, name FROM income ORDER BY amount DESC LIMIT 10";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");

            System.out.println("ID: " + id + ", Name: " + name);

        }
    }

    @When("The top {int} employees with the longest tenure are listed by their respective departments from the staff table in the database.")
    public void theTopEmployeesWithTheLongestTenureAreListedByTheirRespectiveDepartmentsFromTheStaffTableInTheDatabase(int arg0) throws SQLException {
        query = "SELECT id, employee_id, name, surname, department, designation, date_of_joining, DATEDIFF(NOW(), date_of_joining) AS tenure_days FROM staff WHERE department IS NOT NULL ORDER BY department, tenure_days DESC LIMIT 3";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");

            System.out.println("ID: " + id + ", Name: " + name);
        }
    }

    @Then("List the {string} and {string} of students  with  admission numbers between {int} and {int}")
    public void listTheAndOfStudentsWithAdmissionNumbersBetweenAnd(String firstName, String lastName, int startNumber, String endNumber) throws SQLException {

        String query = "SELECT firstname, lastname FROM students WHERE admission_number BETWEEN 18001 AND 18010";
        try {
            ResultSet resultSet = getStatement().executeQuery(query);

            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                System.out.println("First Name: " + firstname + ", Last Name: " + lastname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Then("I display the name information of the highest expense in the expenses table")
    public void iDisplayTheNameInformationOfTheHighestExpenseInTheExpensesTable() throws SQLException {
       // Statement statement = connection.createStatement();
        String query = "SELECT name FROM expenses ORDER BY amount DESC LIMIT 1";

        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            String expenseName = resultSet.getString("name");
            System.out.println("Name of the highest expense: " + expenseName);
        } else {
            System.out.println("No expenses found.");
        }

        //resultSet.close();
       // statement.close();
        //connection.close();
    }


    @When("I add a new call content to the general_calls table")
    public void iAddANewCallContentToTheGeneral_callsTable() throws SQLException {
        String callContent = "This is a new call content.";

        //String insertQuery = "INSERT INTO general_calls (content) VALUES (?)";
        String insertQuery= "INSERT INTO wonderworld_qa2.general_calls (name, contact,date, description,follow_up_date,call_duration, note,call_type) VALUES ('test01','test01','2023-08-16', 'testdescription','2023-08-19','test duration','testnote','testtype');";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, callContent);
            preparedStatement.executeUpdate();
        }


        //connection.close();
    }


    @Then("List the {string} and {string} of students  whose  lastname starts with {string}")
    public void listTheAndOfStudentsWhoseLastnameStartsWith(String mother_name, String mother_occupation, String letter) {
        String query = "SELECT mother_name, mother_occupation FROM students WHERE lastname LIKE 'T%'";
        try {
            ResultSet resultSet = getStatement().executeQuery(query);

            while (resultSet.next()) {
                String motherName = resultSet.getString("mother_name");
                String motherOccupation = resultSet.getString("mother_occupation");
                System.out.println("Mother Name: " + motherName + ", Mother Occupation: " + motherOccupation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Then("List the {string} of students  whose {string} is either {string} or {string} from highest to lowest")
    public void listTheOfStudentsWhoseIsEitherOrFromHighestToLowest(String roll_no, String father_occupation, String doctor, String police) {

        String query = "SELECT roll_no FROM students WHERE father_occupation IN ('Doctor', 'Police') ORDER BY roll_no DESC";

        try {
            ResultSet resultSet = getStatement().executeQuery(query);

            while (resultSet.next()) {
                int rollNo = resultSet.getInt("roll_no");
                System.out.println("Roll No: " + rollNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Then("A specific {string} = {string} entry should be deletable from the visitors_book table")
    public void aSpecificEntryShouldBeDeletableFromTheVisitors_bookTable(String data, String content) throws SQLException {

        if (data == "id" || data == "staff_id" || data == "student_session_id" || data == "no_of_people"){


            String deleteQuery = "DELETE FROM visitors_book " +
                    "WHERE " + data +  " = " + content;

            int rowsAffected = statement.executeUpdate(deleteQuery);

            if (rowsAffected > 0) {
                System.out.println("Entry deleted successfully.");
            } else {
                System.out.println("Entry not found or could not be deleted.");
            }

        }else {

            String deleteQuery = "DELETE FROM visitors_book " +
                    "WHERE " + data +  " = '" + content + "'";

            int rowsAffected = statement.executeUpdate(deleteQuery);

            if (rowsAffected > 0) {
                System.out.println("Entry deleted successfully.");
            } else {
                System.out.println("Entry not found or could not be deleted.");
            }
        }

    }

    @Then("Update fine_amount to {string} in transport_feemaster for records with month {string}")
    public void updateFine_amountToInTransport_feemasterForRecordsWithMonth(String amount, String month) throws SQLException {

        String updateQuery = "UPDATE transport_feemaster SET fine_amount = " + amount + " WHERE month = '" + month + "'";

        int rowsAffected = statement.executeUpdate(updateQuery);

        if (rowsAffected > 0) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Record not found or could not be updated.");
        }
    }

    @Then("List the first {string} employees in the staff table sorted by work experience from the oldest to the newest.")
    public void listTheFirstEmployeesInTheStaffTableSortedByWorkExperienceFromTheOldestToTheNewest(Integer number) throws SQLException {

        String selectQuery = "SELECT * FROM staff ORDER BY work_experience ASC LIMIT 5";
        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            int employeeId = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int workExperience = resultSet.getInt("work_experience");
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("Work Experience: " + workExperience);

        }


    }


    @When("The email, salary, and phone information of the oldest employee is displayed.")
    public void theEmailSalaryAndPhoneInformationOfTheOldestEmployeeIsDisplayed() throws SQLException {
        query = "SELECT email, contact_no AS phone, basic_salary AS salary FROM staff WHERE dob = (SELECT MAX(dob) FROM staff)";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            double salary = resultSet.getDouble("salary");

            System.out.println("Email: " + email + ", Phone: " + phone + ", Salary: " + salary);
        }
        }
    }








