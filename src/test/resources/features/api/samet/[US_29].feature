Feature: As an administrator, I want to access the book information of a book with a given ID through API connection.

  Background:
    Given User sets "api/booksId" path param.

    Scenario Outline: [API_US029-->TC01] API/booksId POST request with valid auth and id returns 200 "Success".

      Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

      Examples:
        | key | value | status | message |
        | id  | 1    | 200    | Success |


    Scenario Outline: [API_US029-->TC02] Invalid auth or id in api/booksId POST returns 403 "failed".

      Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

      Examples:
        | key | value | status | message |
        | id  | 151   |   403  |  failed |



    Scenario: [API_US029-->TC03] Response body's list content must include data for id, book_title, book_no, etc., 11 attributes in total.

      Then Then The contents of the list data with id: "1" in the BookList Response Body should be verified.





