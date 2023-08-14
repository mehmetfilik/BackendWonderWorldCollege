Feature: As an administrator, I want to be able to delete a Vehicle record from the system through API connection.

  Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response
    Given User sets "api/vehicleDelete" path param.
    Then Sending valid authorization and correct data (id) to the api.vehicleDelete endpoint should result in a 200 status code and Success as the response message.

  Scenario: [US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 403 with 'failed' message in response
    Given User sets "api/vehicleDelete" path param.
    Then Sending invalid authorization or incorrect data (id) to the api.vehicleDelete endpoint should result in a 403 status code and failed as the response message.