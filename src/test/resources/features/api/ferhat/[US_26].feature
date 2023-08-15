
@12
Feature: [API_US26] As an administrator, I want to update the registered Alumni information in the system through API connection.


  Scenario: [API_US26-->TC01] When valid authorization information and the correct data (id, student_id, current_email, current_phone, occupation, address, photo) are sent in a PATCH body to the api/alumniUpdate endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Given User sets "api/alumniUpdate" path param.
    Then With "valid" Authorization is sent Patch request must id: "29", update_id_key: "updateId", status: 200 and message: "Success"



  Scenario: [API_US26-->TC02] When invalid authorization information or missing/incorrect data (id) is sent in a PATCH body (student_id, current_email, current_phone, occupation, address, photo) to the api/alumniUpdate endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Given User sets "api/alumniUpdate" path param.
    Then With "invalid" Authorization is sent Patch request must id: "29", update_id_key: "updateId", status: 403 and message: "failed"



  Scenario: [API_US26-->TC03] The updateId in the response body should match the id sent in the PATCH request body to the api/alumniUpdate endpoint.

    Given User sets "api/alumniUpdate" path param.
    Then With "valid" Authorization is sent Patch request must id: "29", update_id_key: "updateId", status: 200 and message: "Success"
    Given User sets "api/alumniId" path param.
    Then After Alumni updating Postrequest sent with "id" must have status: 200 and message: "Success"
