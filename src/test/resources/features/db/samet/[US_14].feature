@sam
Feature: [DB_US14] List the book titles of books in the books table where the author data is 'Rubina Malik' or 'Mien Ali'.

  Scenario: [DB_TC14] List the book titles of books in the books table where the author data is 'Rubina Malik' or 'Mien Ali'.

    Given Start Communication With WonderWorldCollege DataBase
    Then List the book titles of books in the books table where the author data is "Rubina Malik" or "Mien Ali".
    When Close the DataBase