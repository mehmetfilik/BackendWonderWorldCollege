@US_44
Feature: [US_44] As a teacher, I want to be able to access the homework information with a given ID through the API connection.


    Scenario:[API_US44-->TC01] Success Response
      Given a valid authorization is provided
      When a POST request is sent to "apiteacher/homeworkbyId" endpoint
      Then the response status code should be 200
      Then the response message should be "Success"


    Scenario: [API_US44-->TC02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkListbyld, Expect 403 Status and 'failed' Message
      Given POST request is sent to "apiteacher/homeworkbyId" endpoint with invalid authorization
      Then the response status code should be 403
      Then the response message should be "failed"


    Scenario: [API_US44-->TC03] The content of the "list" data in the response body should be verified.
      Given a valid authorization is provided
      When a POST request is sent to "apiteacher/homeworkbyId" endpoint
      Then The contents of the list data within the response body are verified.