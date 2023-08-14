@11
Feature:  [API_US25] As an administrator, I want to create a new Alumni record through API connection.


  Scenario Outline: [API_US25-->TC01] When valid authorization information and the correct data (student_id, current_email, current_phone, occupation, address, photo) are sent in a POST body to the api/alumniAdd endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Given User sets "api/alumniAdd" path param.
    Then In Visitors with "Valid" Authorization sent Post request with "<student_id>", "<current_email>", "<current_phone>", "<occupation>", "<address>", "<photo>","<created_at>" must have <status> and "<message>"
    Examples:
      | student_id | current_email     | current_phone | occupation | address | photo | created_at          | status | message |
      | 29         | deneme@deneme.com | 9809967867    | asdfghnjk  |         | null  | 2023-08-13 09:43:09 | 200    | Success |


  Scenario Outline: [API_US25-->TC02] When invalid authorization information or missing data (student_id, current_email, current_phone, occupation, address, photo) is sent in a POST body to the api/alumniAdd endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Given User sets "api/alumniAdd" path param.
    Then In Visitors with "inValid" Authorization sent Post request with "<student_id>", "<current_email>", "<current_phone>", "<occupation>", "<address>", "<photo>","<created_at>" must have <status> and "<message>"
    Examples:
      | student_id | current_email     | current_phone | occupation | address | photo | created_at          | status | message |
      | 29         | deneme@deneme.com | 9809967867    | asdfghnjk  |         | null  | 2023-08-13 09:43:09 | 403    | failed |


  Scenario: [API_US25-->TC03] The new alumni record should be created on the API, and this can be verified by sending the addId from the response body to the api/alumniId endpoint in a POST body to retrieve the newly created alumni record.

    Given User sets "api/alumniAdd" path param.
    When I want to create a new Alumni record through API connection
