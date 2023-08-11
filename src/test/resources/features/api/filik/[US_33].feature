Feature: [API_US33] As an administrator, I want to access the Visitor List through API connection.

  Scenario: [API_US33-->TC01] Using valid authorization for a GET request to api/visitorsList endpoint should yield a 200 status code with the response message "Success."

    Given User sets "api/visitorsList" path param.
    When With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US33-->TC02] Using invalid authorization for a GET request to api/visitorsList endpoint should yield a 200 status code with the response message "Success."

    Given User sets "api/visitorsList" path param.
    When With "inValid" Authorization is sent Get request must status: "403" and message: "failed"
  @filik
  Scenario: [API_US33-->TC03] The contents of the list data in the response body should be verified. The values of these contents must match the id = 250 in the sent Response body.

    Given User sets "api/visitorsId" path param.
    When The contents of the list data with id: "250" in the Visitors Response Body should be verified.

