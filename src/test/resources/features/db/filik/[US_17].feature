@db
Feature: [DB_US17] Calculate and list the average passing_percentage values from the onlineexam table.

  Scenario: [DB_TC17] Calculate and list the average passing_percentage values from the onlineexam table.

    Given Start Communication With WonderWorldCollege DataBase
    Then Passing Percentage Average
    When Close the DataBase