Feature: [API_US02] As an administrator, I want to access the Visitor Purpose information of a user with a given ID through API connection.

  Scenario Outline: [API_US02-->TC01] When valid authorization info and correct ID data are POSTed to api/visitorsPurposeId, expect 200 status with 'Success' message in response.

    Given User sets "api/visitorsPurposeId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 1     | 200     | Success |

  Scenario Outline: [API_US02-->TC02] When invalid auth or data (ID) is POSTed to api/visitorsPurposeId, expect 403 status with 'failed' message in response.

    Given User sets "api/visitorsPurposeId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 6     | 403     | failed |

  Scenario: [API_US02-->TC03] The content of the list data (ID, visitors_purpose, description, created_at) in the response body should be validated.

    Given The contents of the list data with id: "1" in the VisitorPurpose Response Body should be verified.