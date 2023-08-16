
Feature: As an administrator, I want to access the Session List through API connection.



  Scenario:[API_USO6 -->TC01] Success response

    Given API User sets "api/sessionList" path parameters.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario:[API_USO6 -->TC02] Failed response

    Given User sets "api/sessionList" path param.
    Then With "Invalid" Authorization is sent Get request must status: "403" and message: "failed"

  Scenario:[API_USO6 -->TC03] The Session List response body should contain data with the id of "11"

    Then Confirm the content of Session List response body  data with Id "11".

