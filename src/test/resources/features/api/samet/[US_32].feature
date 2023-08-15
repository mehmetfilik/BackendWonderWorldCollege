@samet
Feature:[API_US32] As an administrator, I want to be able to delete a Books record from the system through API connection.

  Scenario: [API_US32-->TC01] Valid auth info and correct data (id) sent via DELETE to api/booksDelete results in expected 200 status code with response message "Success."

    * User sets "api/booksDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "205", delete_id_key: "DeletedId", status: 200 and message: "Success"


  Scenario: [API_US32-->TC02]

    * User sets "api/booksDelete" path param.
    * With "inValid" Authorization is sent Delete request must id: "473", delete_id_key: "DeletedId", status: 403 and message: "failed"


  Scenario: [API_US32-->TC03]

    * User sets "api/booksDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "206", delete_id_key: "DeletedId", status: 200 and message: "Success"
    Given User sets "api/booksId" path param.
    Then After Books deleting Postrequest sent with "id" must have status: 403 and message: "failed"