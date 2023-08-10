Feature: [API_US34] As an administrator, I want to access the Visitor information of a visitor with a given ID through API connection.

  Background:
    Given User sets "api/visitorsId" path param.
  @API
  Scenario Outline: [API_US34-->TC01] Confirm that using valid authorization and correct data (id) in a POST body to api/visitorsId endpoint yields a 200 status code, with the response body's "message" being "Success."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 3     | 200     | Success |


  Scenario Outline: [API_US34-->TC02] Validate that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the api/visitorsId endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 1     | 403     | failed |


  Scenario:  [API_US34-->TC03] The contents of the list data in the response body should be verified. The values of these contents must match the id in the sent POST body.

    Then The contents of the list data with id: "3" in the Visitors Response Body should be verified.



