Feature: It is used to list all the countries registered in the database.



  Scenario: [US_01-->TC_01]Success Response
    Given User sets "api/visitorsPurposeList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [US_01-->TC_02]Failed Response
    Given User sets "api/visitorsPurposeList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario: [US_01-->TC_03]The content of the lists in the response body should be validated to contain data with ID "1," where the visitors_purpose is "Marketing," and created_at is "2023-01-18 01:07:12."

    Then The contents of the list data with id: "2" in the VisitorPurpose Response Body should be verified.





