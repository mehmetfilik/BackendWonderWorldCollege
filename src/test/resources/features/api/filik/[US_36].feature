@filik
Feature: [API_US36] As an administrator, I want to update a new Visitor record through API connection.

  Scenario: [API_US36-->TC01] Validate PATCH request at 'api/visitorsUpdate' with valid authorization and correct data, ensuring 200 status and "Success" message.

    Given User sets "api/visitorsUpdate" path param.
    Then In Visitors with "Valid" Authorization is sent Patch request must id: "475", update_id_key: "updatedId", status: 200 and message: "Success"


  Scenario: [API_US36-->TC02] Validate PATCH request at 'api/visitorsUpdate' with invalid authorization and incorrect data, ensuring 403 status and "failed" message.

    Given User sets "api/visitorsUpdate" path param.
    Then In Visitors with "inValid" Authorization is sent Patch request must id: "375", update_id_key: "updatedId", status: 403 and message: "failed"

  Scenario: [API_US36-->TC04] Verify updated visitor record via API. Utilize response updateId to fetch details by POSTing to 'api/visitorsId'.

    Given User sets "api/visitorsUpdate" path param.
    Then In Visitors with "Valid" Authorization is sent Patch request must id: "473", update_id_key: "updatedId", status: 200 and message: "Success"
    Given User sets "api/visitorsId" path param.
    Then After Visitors updating Postrequest sent with "id" must have status: 200 and message: "Success"
