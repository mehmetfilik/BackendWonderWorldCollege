@db
Feature: [DB_US04 ] List the firstname and lastname of students in the students table with admission numbers between 18001 and 18010.

  Scenario: [DB_04 --> TC01] List the firstname and lastname of students in the students table with admission numbers between 18001 and 18010.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the "firstname" and "lastname" of students  with  admission numbers between 18001 and 18010
    When Close the DataBase