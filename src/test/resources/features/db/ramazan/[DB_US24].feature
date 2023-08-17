Feature: [DB_US24] Displaying the email, salary, and phone information of the oldest employee.

  @da
  Scenario: [DB_US24]
    Given Start Communication With WonderWorldCollege DataBase
    When The email, salary, and phone information of the oldest employee is displayed.
    Then Close the DataBase