Feature: [API_US04] As an administrator, I want to update the registered visitor purpose information in the system through API connection.

  Scenario Outline: [API_US04-->TC_01, TC_03] Valid authorization info, correct data (id, visitors_purpose, description) sent in PATCH body to api/visitorsPurposeUpdate yields status 200 with response message "Success."

    Given User sets "api/visitorsPurposeUpdate" path param.
    Then Patchrequest sent with "<key1>" , "<value1>", "<key2>" and "<value2>","<key3>" and "<value3>" and must have "<status>" and "<message>"

    Examples:
      | key1 | value1 | key2              | value2         | key3            | value3                 |status | message  |
      | id   | 10     | visitors_purpose  | purpose update | description     | came for student visit |200    | Success  |


  Scenario Outline: [API_US04-->TC_02] Invalid auth or missing/wrong data (id) in PATCH body (with visitors_purpose, description) to api/visitorsPurposeUpdate results in status 403 with response message "failed.

    Given User sets "api/visitorsPurposeUpdate" path param.
    Then Patchrequest sent with invalid Authorization with "<key1>" , "<value1>", "<key2>", "<value2>","<key3>" and "<value3>" and must have "<status>" and "<message>"

    Examples:
      | key1 | value1 | key2              | value2         | key3            | value3                 |status | message |
      | id   | 1001   | visitors_purpose  | purpose update | description     | came for student visit |403    | failed  |

  Scenario: [API_US4-->TC04] Validate successful API update of visitor purpose record by confirming with the returned updateId in response body for a POST request to api/visitorsPurposeId endpoint to verify updated record.
    
    Given Checking the id updated with Postrequest id: 10 from API



