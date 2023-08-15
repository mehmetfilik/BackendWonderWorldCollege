Feature: [DB_US15] List the books from the books table where the quantity (qty) value is between 100 and 500.

  Scenario: [DB_TC15] List the books from the books table where the quantity (qty) value is between 100 and 500.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the books from the books table where the quantity qty value is between "100" and "500".
    When Close the DataBase