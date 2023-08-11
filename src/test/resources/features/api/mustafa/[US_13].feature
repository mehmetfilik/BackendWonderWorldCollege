Feature: [US_13]As an administrator, I want to access the Vehicle List through API connection.

  Background:
    Given User sets "api/vehicleList" path param

  Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response

    And For "vehicleList" is sent Get request
    And Verifies that the status code in Response is 200
    Then Verifies that "message" is "Success" in Response




  Scenario:[US_13-->TC_02] Sending a GET request with invalid authorization to api/vehicleList
    When With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario:[US_13-->TC_03]  The lists content in the response body should be able to verify the data content with id=1.
    And Verify the data content with lists content id=1 in the response body.










