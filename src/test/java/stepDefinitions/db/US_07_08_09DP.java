package stepDefinitions.db;

import io.cucumber.java.en.Then;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static utilities.DB_Utils.createConnection;
import static utilities.DB_Utils.getStatement;

public class US_07_08_09DP {
    String query;
    ResultSet resultSet;
    Connection connection;
    Statement statement;

    @Then("Sort the contents with role=parent in the users table according to the user id from largest to smallest.")
    public void sort_the_contents_with_role_parent_in_the_users_table_according_to_the_user_id_from_largest_to_smallest() throws SQLException {

        query = "SELECT * FROM users";

        resultSet = statement.executeQuery(query);
        resultSet.next();
        System.out.println(resultSet.getString("name")+""+resultSet.getInt("id"));


    }
}