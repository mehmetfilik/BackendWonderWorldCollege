@23
Feature: [US_46]As a teacher, I want to update registered homework information through the API.

    Scenario: [API_US46-->TC_01] Success response
        Given a valid authorization is provided
        When a PATCH request is sent to "apiteacher/homeworkUpdate" endpoint
        Then the response status code should be 200
        Then the response message should be "Success"

    Scenario: [API_US46-->TC_02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkUpdate, Expect 403 Status and 'failed' Message
        Given PATCH request is sent to "apiteacher/homeworkUpdate" endpoint with invalid authorization
        Then the response status code should be 403
        Then the response message should be "failed"

    Scenario: [API_US046-->TC_03]The "updateId" in the response body should match the "id" in the PATCH request body sent to apiteacher/homeworkUpdate.
        Given a valid authorization is provided
        When a PATCH request is sent to "apiteacher/homeworkUpdate" endpoint
        Then Successful verification of the update record is confirmed.

    Scenario: [API_US046-->TC_04] The updated homework record should be confirmed via the API.
          Given a valid authorization is provided
          When a PATCH request is sent to "apiteacher/homeworkUpdate" endpoint
          Then The update of the homework entry to be modified through the API is performed, and its confirmation is validated via the API
