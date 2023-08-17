Feature: [DB_US10] A specific entry should be deletable from the visitors_book table

  Scenario: [DB_US10-->TC01] A specific entry should be deletable from the visitors_book table

    Given Start Communication With WonderWorldCollege DataBase
    Then A specific "name" = "ghghgh" entry should be deletable from the visitors_book table
    When Close the DataBase

  Scenario: [DB_US11-->TC01]

    Given Start Communication With WonderWorldCollege DataBase
    Then  Update fine_amount to "200.00" in transport_feemaster for records with month "October"
    When Close the DataBase

  Scenario: [DB_US12-->TC01] List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.

    Given Start Communication With WonderWorldCollege DataBase
    When Close the DataBase