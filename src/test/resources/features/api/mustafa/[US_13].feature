Feature: [US_13]As an administrator, I want to access the Vehicle List through API connection.


  Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response
    Given User sets "api/vehicleList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario:[US_13-->TC_02] Failed Response
    Given User sets "api/vehicleList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


Scenario: The lists content in the response body should be able to verify the data content with id=1
  Given User sets "api/vehicleList" path param.
  Then The contents of the list data with id: "1" in the vehicleList Response Body should be verified.











