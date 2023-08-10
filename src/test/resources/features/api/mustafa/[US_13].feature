Feature: [US_13]As an administrator, I want to access the Vehicle List through API connection.
  @mustafa
  Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response

    Given User sets "api/vehicleList" path param.
    And For "vehicleList" is sent Get request.
