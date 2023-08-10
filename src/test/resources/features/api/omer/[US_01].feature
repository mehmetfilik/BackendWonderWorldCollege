Feature: It is used to list all the countries registered in the database.


  Scenario: Success Response

    Given User sets "api/visitorsPurposeList" path param.
    Then For "VisitorsPurpose" is sent Get request.