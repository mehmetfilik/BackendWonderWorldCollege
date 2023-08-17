@db
Feature: [DB_US16] List the last 10 records from the online_admissions table

  Scenario: [DB_TC16] List the last 10 records from the online_admissions table

    Given Start Communication With WonderWorldCollege DataBase
    Then List the last 10 records from the online_admissions table
    When Close the DataBase