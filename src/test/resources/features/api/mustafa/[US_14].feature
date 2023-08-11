@mustafa
Feature [US_14]As an administrator, I want to access the Vehicle information of a vehicle with a given ID through API connection.

  Background:
    Given User sets "api/vehicleId" path param.

  Scenario Outline: [API_US14-->TC01] Confirm that using valid authorization and correct data (id) in a POST body to api/vehicleId endpoint yields a 200 status code, with the response body's "message" being "Success."

   Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message  |
      | id  | 1     | 200    | Success |

  Scenario Outline: [API_US14-->TC02] Validate that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the api/vehicleId endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 50    | 403    | failed  |
