@demoDB
Feature: e2e DATABASE TESTING

  Scenario: [DB_US01-->TC_01]Verify that there are 11 users in the chat_users table with create_staff_id equal to 1.
    Given Start Communication With WonderWorldCollege DataBase
    Then Verify that there are 11 users in the chat_users table with create_staff_id equal to 1
    When Close the DataBase

  Scenario:[DB_US02_TC_01] List the IDs of contents from the class_sections table where the class_id and section_id values are equal.
    Given Start Communication With WonderWorldCollege DataBase
    Then List the IDs of items in the class_sections table where the values of class_id and section_id are the same.
    When Close the DataBase

  Scenario: [DB_US03-->TC_01] Verify that the email of the student in the students table with firstname Brian and lastname Kohlar is brain@gmail.com.
    Given Start Communication With WonderWorldCollege DataBase
    Then Verify that the email of the student in the students table with firstname: "Brian" and lastname: "Kohlar" is email:"brain@gmail.com".

#  Scenario: [DB_04 --> TC01] List the firstname and lastname of students in the students table with admission numbers between 18001 and 18010.
#    Given Start Communication With WonderWorldCollege DataBase
#    Then List the "firstname" and "lastname" of students  with  admission numbers between 18001 and 18010
#    When Close the DataBase

  Scenario: [DB_05 --> TC01] List the mother_name and mother_occupation values of the students in the student table whose lastname starts with 'T'
    Given Start Communication With WonderWorldCollege DataBase
    Then List the "mother_name" and "mother_occupation" of students  whose  lastname starts with "T"
    When Close the DataBase

  Scenario: [DB_06 --> TC01] List the roll_no values of the students whose father_occupation is either Doctor or Police from highest to lowest
    Given Start Communication With WonderWorldCollege DataBase
    Then List the "roll_no" of students  whose "father_occupation" is either "Doctor" or "Police" from highest to lowest
    When Close the DataBase

  Scenario:[DB_TC07]List the contents in the users table with role equal to parent, sorted by user id in descending order.
    Given Start Communication With WonderWorldCollege DataBase
    Then Sort the contents with role=parent in the users table according to the user id from largest to smallest.
    Then Close the DataBase

  Scenario: [DB_TC08] The name information for the specified id in the topic table should be updatable.
    Given Start Communication With WonderWorldCollege DataBase
    Then The name information for the specified id in the topic table should be updatable
    And Close the DataBase

  Scenario: [DB_TC09] A new record should be added to the transport_route table.
    Given Start Communication With WonderWorldCollege DataBase
    Then A new record should be added to the transport_route table.
    And Close the DataBase

#  Scenario: [DB_US10-->TC01] A specific entry should be deletable from the visitors_book table
#
#    Given Start Communication With WonderWorldCollege DataBase
#    Then A specific "name" = "ghghgh" entry should be deletable from the visitors_book table
#    When Close the DataBase

  Scenario: [DB_US11-->TC01]

    Given Start Communication With WonderWorldCollege DataBase
    Then  Update fine_amount to "200.00" in transport_feemaster for records with month "October"
    When Close the DataBase

  Scenario: [DB_US12-->TC01] List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.

    Given Start Communication With WonderWorldCollege DataBase
    When Close the DataBase

  Scenario: [DB_TC13] List the email addresses of records in the online_admissions table where the firstname contains the word 'al'.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the email addresses of records in the online_admissions table where the firstname contains the word "al"
    When Close the DataBase

  Scenario: [DB_TC14] List the book titles of books in the books table where the author data is 'Rubina Malik' or 'Mien Ali'.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the book titles of books in the books table where the author data is "Rubina Malik" or "Mien Ali".
    When Close the DataBase

  Scenario: [DB_TC15] List the books from the books table where the quantity (qty) value is between 100 and 500.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the books from the books table where the quantity qty value is between "100" and "500".
    When Close the DataBase



  Scenario: [DB_TC16] List the last 10 records from the online_admissions table

    Given Start Communication With WonderWorldCollege DataBase
    Then List the last 10 records from the online_admissions table
    When Close the DataBase

  Scenario: [DB_TC17] Calculate and list the average passing_percentage values from the onlineexam table.

    Given Start Communication With WonderWorldCollege DataBase
    Then Passing Percentage Average
    When Close the DataBase

  Scenario: [DB_TC18] List the number of students in the onlineexam_students table based on unique student_session_id values.

    Given Start Communication With WonderWorldCollege DataBase
    Then Unique Student Count on onlineexam_students
    When Close the DataBase

  Scenario: [DB_19-->TC_01] List 5 Longest Email Values from Students Table
    Given Start Communication With WonderWorldCollege DataBase
    When a query is executed to retrieve the 5 longest (text) values from the email column in the students table
    When Close the DataBase

  Scenario: [DB_20-->TC_01] Display the name information of the highest expense in the expenses table.
    Given Start Communication With WonderWorldCollege DataBase
    Then I display the name information of the highest expense in the expenses table
    When Close the DataBase

#  Scenario: [DB_21-->TC_01] Add a new call content to the general_calls table.
#    Given Start Communication With WonderWorldCollege DataBase
#    When I add a new call content to the general_calls table
#    When Close the DataBase

  Scenario:[DB_US22]
    Given Start Communication With WonderWorldCollege DataBase
    When The top 10 individuals with the highest amount value are listed from the Income table
    Then Close the DataBase

  Scenario: [DB_US23]
    Given Start Communication With WonderWorldCollege DataBase
    When The top 3 employees with the longest tenure are listed by their respective departments from the staff table in the database.
    Then Close the DataBase

  Scenario: [DB_US24]
    Given Start Communication With WonderWorldCollege DataBase
    When The email, salary, and phone information of the oldest employee is displayed.
    Then Close the DataBase










