Feature: [API_US34] As an administrator, I want to access the Visitor information of a visitor with a given ID through API connection.
@API
  Scenario Outline: [API_US34-->TC01] Confirm that using valid authorization and correct data (id) in a POST body to api/visitorsId endpoint yields a 200 status code, with the response body's "message" being "Success."
    
    Given User sets "api/visitorsId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 3     | 200     | Success |


  Scenario Outline: [API_US34-->TC02] Validate that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the api/visitorsId endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."

    Given User sets "api/visitorsId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 1     | 403     | failed |



