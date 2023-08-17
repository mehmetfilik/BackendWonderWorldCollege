
Feature: As an administrator, I want to access the Alumni Events List through API connection.

  Scenario:[API_USO7 -->TC01] Success response

    Given User sets "api/alumniEventsList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario:[API_USO7 -->TC02] Failed response

    Given User sets "api/alumniEventsList" path param.
    Then With "Invalid" Authorization is sent Get request must status: "403" and message: "failed"
  Scenario:[API_USO7 -->TC03] The Alumni Event List response body  should contain data with the id of "2"

    Then Confirm the content of Alumni Event List response body list data with Id "2".
