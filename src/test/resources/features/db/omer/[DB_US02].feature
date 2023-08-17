Feature: [DB_US02]List the IDs of contents from the class_sections table where the class_id and section_id values are equal.


  Scenario:[DB_US02_TC_01] List the IDs of contents from the class_sections table where the class_id and section_id values are equal.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the IDs of items in the class_sections table where the values of class_id and section_id are the same.
    When Close the DataBase
