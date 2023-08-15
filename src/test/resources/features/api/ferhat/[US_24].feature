

Feature: [API_US24] As an administrator, I want to access the Student List through API connection.

  Background:
    Given User sets "api/alumniId" path param.

  Scenario Outline: [API_US24-->TC01] When a valid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 2     | 200    | Success |


  Scenario Outline: [API_US24-->TC02] When invalid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 1  | 403    | failed  |


    Scenario: [API_US24-->TC03] The new alumni record should be created on the API, and this can be verified by sending the addId from the response body to the api/alumniId endpoint in a POST body to retrieve the newly created alumni record.

      Then The contents of the list data with id: "2" in the AlumniId Response Body should be verified.