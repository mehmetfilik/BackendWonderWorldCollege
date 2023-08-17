@dbUS20
Feature:[DB_US20] Display the name information of the highest expense in the expenses table.
  Scenario: Display the name information of the highest expense in the expenses table.
    Given Start Communication With WonderWorldCollege DataBase
    Then I display the name information of the highest expense in the expenses table
    When Close the DataBase