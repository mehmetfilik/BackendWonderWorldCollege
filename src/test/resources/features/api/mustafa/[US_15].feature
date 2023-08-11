Feature: [US_15] As an administrator, I want to create a new Vehicle record through API connection.

  Background:
    Given User sets "api/vehicleAdd" path param.

  Scenario:[US_15-->TC_01] When valid authorization info and correct data are POSTed to api/vehicleAdd, expected status code: 200, and response body message should be 'Success'.
    Then User posts valid authorization info and correct data to api.vehicleAdd, expecting status code 200 and confirming response body "message" as "Success".

  Scenario : [API_US15-->TC02] Validate that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the api/vehicleAdd endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Then When invalid auth or incomplete data is sent to api.vehicleAdd, confirm status code 403 and response "message" as "failed".