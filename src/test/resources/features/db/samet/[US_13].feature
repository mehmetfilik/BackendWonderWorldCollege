Feature: [DB_US13] List the email addresses of records in the online_admissions table where the firstname contains the word 'al'.

  Scenario: [DB_TC13] List the email addresses of records in the online_admissions table where the firstname contains the word 'al'.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the email addresses of records in the online_admissions table where the firstname contains the word "al"
    When Close the DataBase



