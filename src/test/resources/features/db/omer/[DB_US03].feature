Feature: [DB_US03] Verify that the email of the student in the students table with firstname Brian and lastname Kohlar is brain@gmail.com.

  @omer12
  Scenario: [DB_US03-->TC_01] Verify that the email of the student in the students table with firstname Brian and lastname Kohlar is brain@gmail.com.

    Given Start Communication With WonderWorldCollege DataBase
    Then Verify that the email of the student in the students table with firstname: "Brian" and lastname: "Kohlar" is email:"brain@gmail.com".