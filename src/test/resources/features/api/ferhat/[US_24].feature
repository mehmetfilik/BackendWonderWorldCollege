Feature: [API_US23] As an administrator, I want to access the Student List through API connection.

  Background:
    Given User sets "api/alumniId" path param.

  Scenario Outline: [API_US23-->TC01] When a valid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 2     | 200    | Success |


  @12
  Scenario Outline: [API_US23-->TC02] When invalid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 100   | 403    | failed  |