

Feature: [DB_US10] A specific entry should be deletable from the visitors_book table

  Scenario: [DB_US10-->TC01] A specific entry should be deletable from the visitors_book table

    Given Start Communication With WonderWorldCollege DataBase
    Then A specific "name" = "ghghgh" entry should be deletable from the visitors_book table
    When Close the DataBase