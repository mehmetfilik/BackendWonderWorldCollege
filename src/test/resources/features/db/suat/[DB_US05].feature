Feature: List the mother_name and mother_occupation values of the student in the students table whose lastname starts with 'T'.

  Scenario: [DB_05 --> TC01] List the mother_name and mother_occupation values of the students in the student table whose lastname starts with 'T'

    Given Start Communication With WonderWorldCollege DataBase
    Then List the "mother_name" and "mother_occupation" of students  whose  lastname starts with "T"
    When Close the DataBase