Feature: [DB_US12] List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.

  Scenario: [DB_US12-->TC01] List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.
    When Close the DataBase