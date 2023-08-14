Feature:[US_16] As an administrator, I want to update the registered Vehicle information in the system through API connection.

  Scenario:[US_16-->TC_01] Valid authorization and correct data in PATCH request to api.vehicleUpdate should return 200 status and Success message.
    Given User sets "api/vehicleUpdate" path param.
    Then send endpoint valid PATCH body and verify that the returned status code is 200 and the "message" information is "Success"

Scenario: [US_16-->TC_02] Valid authorization and correct data in PATCH request to api.vehicleUpdate should return 200 status and Success message.
  Given User sets "api/vehicleUpdate" path param.
  Then send endpoint invalid PATCH body and verify that the returned status code is 403 and the "message" information is "failed"

  Scenario:[US_16-->TC_03] The response body's updateId must match the id sent in the PATCH request body to the api.vehicleUpdate endpoint.
    Given User sets "api/vehicleUpdate" path param.
    Then The updateId in the response must match the id in the PATCH request body sent to the api.vehicleUpdate endpoint.

    Scenario: [US_16-->TC_03] The successful update of the desired vehicle record via the API should be validated.
      Given User sets "api/vehicleUpdate" path param.
      Then Update the vehicle registration with ID 28 and verify that it has been updated