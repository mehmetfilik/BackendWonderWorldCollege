@[US_47]
Feature: [US_47]As a teacher, I want to delete a homework record in the system via API.


  Scenario:[US_47-->TC_01] Success response
    Given a valid authorization is provided
    When a DELETE request body is sent to "apiteacher/homeworkDelete" endpoint
    Then the response status code should be 200
    Then the response message should be "Success"


  Scenario:[US_47-->TC_02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkDelete, Expect 403 Status and 'failed' Message
    Given DELETE request is sent to "apiteacher/homeworkDelete" endpoint with invalid authorization
    Then the response status code should be 403
    Then the response message should be "failed"



    Scenario:[US_47-->TC_03_04]The "DeletedId" in the response body should match the "id" in the DELETE request body sent to apiteacher/homeworkDelete.
          Given a valid authorization is provided
          When a DELETE request body is sent to "apiteacher/homeworkDelete" endpoint
          Then Verify DeletedId in response body matches id in DELETE request body to endpoint.
          Then The deletion of the record is confirmed through the API

