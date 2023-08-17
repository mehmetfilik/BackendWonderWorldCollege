Feature:[API_US31] As an administrator, I want to update the registered Books information in the system through API connection.

  Scenario: [API_US31-->TC01] Valid auth info and correct data (book info) sent via PATCH to api/booksUpdate results in expected 200 status code with response message "Success.

    Given User sets "api/booksUpdate" path param.
    Then in books with "Valid" Authorization is sent Patch request must id: "85", update_id_key: "updateId", status: 200 and message: "Success"



  Scenario: [API_US31-->TC02] invalid auth or incomplete/incorrect data in PATCH to api/booksUpdate leads to expected 403 status with response message "failed."

    Given User sets "api/booksUpdate" path param.
    Then in books with "inValid" Authorization is sent Patch request must id: "85", update_id_key: "updateId", status: 403 and message: "failed"

  Scenario: [API_US31-->TC04] To further validate that the book record was updated, we can send a POST body to the api/booksId endpoint using the returned updateId to check the updated details of the book.

    Given User sets "api/booksUpdate" path param.

    Then in books with "Valid" Authorization is sent Patch request must id: "87", update_id_key: "updateId", status: 200 and message: "Success"

    Given User sets "api/booksId" path param.
    Then After Books updating Postrequest sent with "id" must have status: 200 and message: "Success"