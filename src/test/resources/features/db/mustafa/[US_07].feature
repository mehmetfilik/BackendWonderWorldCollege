Feature: [DB_US07]List the contents in the users table with role equal to parent, sorted by user id in descending order.

  Scenario:[DB_TC07]List the contents in the users table with role equal to parent, sorted by user id in descending order.
    Given Start Communication With WonderWorldCollege DataBase
    Then Sort the contents with role=parent in the users table according to the user id from largest to smallest.
    Then Close the DataBase