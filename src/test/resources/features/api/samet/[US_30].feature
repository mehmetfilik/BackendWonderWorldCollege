@sy
Feature: [API_US30] As an administrator, I want to create a new Books record through API connection.


    Scenario Outline: [API_US30-->TC01] Valid auth info and correct book data sent via POST to api/booksAdd returns a 200 status code with "Success" message.

      Given User sets "api/booksAdd" path param.
      Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"

      Examples:

      | book_title                                | book_no   | isbn_no   | subject      | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    |    001    |   sassd      | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |


    Scenario Outline: [API_US30-->TC02] Invalid auth info or incomplete data (book info) in POST to api/booksAdd yields expected 403 status code with response message "failed."

      Given User sets "api/booksAdd" path param.
      Then Postrequest with invalid Authorization sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"

      Examples:

        | book_title                                | book_no   | isbn_no   | subject | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
        | Multiplication and Division Grades 3-4 23 | 788789    |  001      |  sassd  | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 403    | failed  |


    Scenario Outline: [API_US30-->TC03] The new books record that is intended to be created through the API should be verified through the API.

      Given User sets "api/booksAdd" path param.
      Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"
      Given User sets "api/booksAdd" path param.
      Then in Books Postrequest sent with "id" must have status: 200 and message: "Success"
      Examples:
        | book_title                                | book_no   | isbn_no   | subject | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
        | Multiplication and Division Grades 3-4 23 | 788789    | 001       | sassd   | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |


