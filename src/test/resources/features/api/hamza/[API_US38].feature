@apiUS38
Feature: Testing api/getNotice Endpoint
  @apiUS38_1
  Scenario: [US_38-->TC_01]Successful GET Request to api/getNotice with Valid Authorization
    Given User sets "api/getNotice" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"

  @apiUS38_2
  Scenario:[US_13-->TC_02] Failed Response
    Given User sets "api/getNotice" path param.
    Then When a Get request is made with invalid Authorization, the status must be 403, and the message must be failed.

  @apiUS38_3
  Scenario: The lists content in the response body should be able to verify the data content with id=1
    #Given User sets "api/getNotice" path param.
    Then The contents of the list data with id: "34" in the Notice List Response Body should be verified.