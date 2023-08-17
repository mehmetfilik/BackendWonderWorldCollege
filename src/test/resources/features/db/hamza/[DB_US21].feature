@dbUS21
Feature:[DB_US21] Add a new call content to the general_calls table.
  Scenario: Add a new call content to the general_calls table.
    Given Start Communication With WonderWorldCollege DataBase
    When I add a new call content to the general_calls table
    When Close the DataBase