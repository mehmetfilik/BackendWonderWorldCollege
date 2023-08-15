
Feature: [US_17] As an administrator, I want to be able to delete a Vehicle record from the system through API connection.

  Scenario: [US_17-->TC_01] Valid authorization, correct ID in DELETE request to api/vehicleDelete should return 200 status, 'Success' message.
    Given User sets "api/vehicleDelete" path param.
    Then send endpoint valid DELETE body and verify that the returned status code is 200 and the "message" information is "Success"

Scenario:[US_17-->TC02] Failed Response
  Given User sets "api/vehicleDelete" path param.
  Then When invalid auth or incomplete data is sent to api.vehicleDelete, confirm status code 403 and response "message" as "failed".

  Scenario: [US_17-->TC_03] It should be verified that the Deleted information in the response body is the same as the id information in the request body.
    Given User sets "api/vehicleDelete" path param.
    Then It is verified that the Deleted information in the response body is the same as the id in the request body.

    Scenario: [US_17-->TC04] It should be verified via API that the vehicle record to be deleted via API is deleted.
      Given User sets "api/vehicleId" path param.
      Then It is verified via the API that the vehicle record to be deleted via the API is deleted.


 #Feature: As an administrator, I want to be able to delete a Vehicle record from the system through API connection.

  #Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response
   # Given User sets "api/vehicleDelete" path param.
    #Then Sending valid authorization and correct data (id) to the api.vehicleDelete endpoint should result in a 200 status code and Success as the response message.

  #Scenario: [US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 403 with 'failed' message in response
   # Given User sets "api/vehicleDelete" path param.
    #Then Sending invalid authorization or incorrect data (id) to the api.vehicleDelete endpoint should result in a 403 status code and failed as the response message.

