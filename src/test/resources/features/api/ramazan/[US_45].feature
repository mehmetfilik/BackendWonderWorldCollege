@[US_45]
Feature: [US_45]As a teacher, I want to create a new homework record via API.

   Scenario:[API_US45-->TC01] Success Response
      Given a valid authorization is provided
      When a POST request body is sent to "apiteacher/homeworkAdd" endpoint
      Then the response status code should be 200
      Then the response message should be "Success"


  Scenario: [API_US45-->TC02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkAdd, Expect 403 Status and 'failed' Message
      Given POST request is sent to "apiteacher/homeworkAdd" endpoint with invalid authorization
      Then the response status code should be 403
      Then the response message should be "failed"


  Scenario: [API_US45-->TC03] The newly created homework record should be verified via the API.
      Given a valid authorization is provided
      When a POST request body is sent to "apiteacher/homeworkAdd" endpoint
      Then The validity of a created record is verified through the API
