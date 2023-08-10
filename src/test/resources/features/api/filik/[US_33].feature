Feature: [API_US33] As an administrator, I want to access the Visitor List through API connection.

  Scenario: [API_US33-->TC01] Using valid authorization for a GET request to api/visitorsList endpoint should yield a 200 status code with the response message "Success."

    Given User sets "api/visitorsList" path param.
    Then For "VisitorsList" is sent Get request.