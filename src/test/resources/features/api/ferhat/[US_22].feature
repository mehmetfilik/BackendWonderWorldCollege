

Feature: [API_US22] As an administrator, I want to access the Alumni List through API connection

  Scenario: [API_US22-->TC01] Success Response
    
    Given User sets "api/alumniList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US22-->TC02] Failed Response

    Given User sets "api/alumniList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario: [API_US22-->TC03] The content of the lists in the response body should contain the following data: (id: "2", student_id: "41", current_email: "rohan@gmail.com", current_phone: "0808080707", occupation:"", address: "", photo:null, created_at: "2023-03-11 03:04:50").This should be verified to ensure the correct response data.

    Given User sets "api/alumniId" path param.
    Then The contents of the list data with id: "2" in the Alumni Response Body should be verified.

