Feature:[API_US32] As an administrator, I want to be able to delete a Books record from the system through API connection.
@sy
  Scenario Outline: [API_US32-->TC01] Valid auth info and correct data (id) sent via DELETE to api/booksDelete results in expected 200 status code with response message "Success."
  Given User sets "api/booksAdd" path param.
  Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"
  * User sets "api/booksDelete" path param.
  * With "Valid" Authorization is sent Delete request must id: "371", delete_id_key: "DeletedId", status: 200 and message: "Success"

  Examples:

    | book_title                                | book_no   | isbn_no   | subject      | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
    | Multiplication and Division Grades 3-4 23 | 788789    |    001    |   sassd      | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |



  Scenario: [API_US32-->TC02]

    * User sets "api/booksDelete" path param.
    * With "inValid" Authorization is sent Delete request must id: "473", delete_id_key: "DeletedId", status: 403 and message: "failed"


  Scenario Outline: [API_US32-->TC03]
    Given User sets "api/booksAdd" path param.
    Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"
    * User sets "api/booksDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "372", delete_id_key: "DeletedId", status: 200 and message: "Success"
    Given User sets "api/booksId" path param.
    Then After Books deleting Postrequest sent with "id" must have status: 403 and message: "failed"
    Examples:

      | book_title                                | book_no   | isbn_no   | subject      | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    |    001    |   sassd      | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |

