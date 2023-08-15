


Feature: [API_US23] As an administrator, I want to access the Student List through API connection.

  Scenario: [API_US22-->TC01] When a valid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Given User sets "api/studentList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US22-->TC02]  When invalid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Given User sets "api/studentList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario: [API_US22-->TC03] Response Data Validation for class id "3"
    Given User sets "api/alumniId" path param.
    Then The contents of the list data with id: "3" in the AlumniStudent Response Body should be verified.

