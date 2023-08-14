@db
Feature: [DB_US18] List the number of students in the onlineexam_students table based on unique student_session_id values.

  Scenario: [DB_TC18] List the number of students in the onlineexam_students table based on unique student_session_id values.

    Given Start Communication With WonderWorldCollege DataBase
    Then Unique Student Count on onlineexam_students
    When Close the DataBase