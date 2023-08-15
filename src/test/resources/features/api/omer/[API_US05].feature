Feature: [API_US05] As an administrator, I want to be able to delete a visitor purpose record from the system through API connection.

  Scenario: [API_US05-->TC01,TC03] When valid authorization information and correct data (id) are sent in the DELETE body to the api/visitorsPurposeDelete endpoint, the expected status code is 200, and the message in the response body should be "Success.

    Given Creating a new record with Post Request and deleting it with deleterequest.


  Scenario:  [API_US05-->TC02] When invalid authorization information or wrong data (id) is sent in the DELETE body to the api/visitorsPurposeDelete endpoint, the expected status code is 403, and the message in the response body should be "failed.

    Given Creating a new record with postrequest and verifying that it has not been deleted with invalid authorization


  Scenario: [API_US05-->TC03] The successful deletion of the visitor purpose record via the API should be validated.

    Given Checking for a deleted record