@filik
Feature: [API_US37] As an administrator, I want to be able to delete a Visitors' record from the system through API connection.

  Scenario: [API_US37-->TC01] Confirm DELETE request: valid auth, correct data (id), 200 status, "Success" message at 'api/visitorsDelete'.

    * User sets "api/visitorsDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "475", delete_id_key: "deletedId", status: 200 and message: "Success"

  Scenario: [API_US37-->TC02] Confirm DELETE request: invalid auth, incorrect data (id), 200 status, "Success" message at 'api/visitorsDelete'.

    * User sets "api/visitorsDelete" path param.
    * With "inValid" Authorization is sent Delete request must id: "473", delete_id_key: "deletedId", status: 403 and message: "failed"


  Scenario: [API_US37-->TC04] Confirm DELETE request: valid auth, correct data (id), 200 status, "Success" message at 'api/visitorsDelete'.

    * User sets "api/visitorsDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "482", delete_id_key: "deletedId", status: 200 and message: "Success"
    Given User sets "api/visitorsId" path param.
    Then After Visitors deleting Postrequest sent with "id" must have status: 403 and message: "failed"