Feature: [US_13]As an administrator, I want to access the Vehicle List through API connection.


  Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response

    Given User sets "api/vehicleList" path param
    And For "vehicleList" is sent Get request
    And Verifies that the status code in Response is 200
    Then Verifies that "message" is "Success" in Response

  @mustafa
  Scenario: Sending a GET request with invalid authorization to api/vehicleList
    Given User sets "api/vehicleList/10" path param
    When With "invalid" Authorization is sent Get request must status: "403" and message: "failed"






