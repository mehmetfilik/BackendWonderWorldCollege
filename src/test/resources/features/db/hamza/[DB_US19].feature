@dbUS19
Feature:[DB_US19] Retrieval of Longest Email Values from Students Table
  Scenario: [DB_19-->TC_01] List 5 Longest Email Values from Students Table
    Given Start Communication With WonderWorldCollege DataBase
    When a query is executed to retrieve the 5 longest (text) values from the email column in the students table
     When Close the DataBase