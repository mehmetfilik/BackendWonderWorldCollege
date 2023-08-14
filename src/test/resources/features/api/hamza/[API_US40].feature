api/addNotice
Feature: As an administrator, I want to create a new visitor purpose record through API connection.

  Scenario: When valid auth info & correct data (purpose, description) are POSTed to api/visitorsPurposeAdd, expect 200 status & 'Success' message in response.

    Given User sets "api/addNotice" path param.
    Then Postrequest sent with "testnotice", "testtitle", "testdescription", "testslug"
    #Then the response status code should be 200 And the response body's message should be "Success"


