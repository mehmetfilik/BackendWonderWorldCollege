Feature: List the roll_no values of students in the students table whose father_occupation is either Doctor or Police, ordered from highest to lowest.

  Scenario: [DB_06 --> TC01] List the roll_no values of the students whose father_occupation is either Doctor or Police from highest to lowest

    Given Start Communication With WonderWorldCollege DataBase
    Then List the "roll_no" of students  whose "father_occupation" is either "Doctor" or "Police" from highest to lowest
    When Close the DataBase