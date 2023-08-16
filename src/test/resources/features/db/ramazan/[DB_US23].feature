Feature: [DB_US23] The top employees from the staff table


  @asd
  Scenario: [DB_US23]
    Given Start Communication With WonderWorldCollege DataBase
    When The top 3 employees with the longest tenure are listed by their respective departments from the staff table in the database.
    Then Close the DataBase