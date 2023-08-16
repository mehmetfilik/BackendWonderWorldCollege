
Feature: As an administrator, I want to access the Alumni Events information with a given ID through API connection.

  Background:
    Given API User sets "api/alumniEventsId" path parameters.

  Scenario Outline: [API_US09-->TC01] Confirm that using valid authorization and correct data (id) in a POST body to  api/alumniEventsId endpoint produce a 200 status code, with the response body's "message" being "Success."
    Then Send a Post request  with "valid authorization" "<key>" and <value> should have "<status>" and "<message>"
    Examples:
      | key  | value | status | message |
      | id   | 930     | 200    | Success |

  Scenario Outline: [API_US09-->TC02] Verify that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the  api/alumniEventsId endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Then Send a Post request  with "invalid authorization" "<key>" and <value> should have "<status>" and "<message>"
    Examples:
      | key  | value | status | message |
      | id   | 5     | 403    | failed  |

  Scenario: [API_US09-->TC03] Validate content of Alumni Events information by ID in the response body

    Then The content of the list data should be validated with id:"930" in the Alumni Events