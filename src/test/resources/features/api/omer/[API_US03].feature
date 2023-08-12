Feature: As an administrator, I want to create a new visitor purpose record through API connection.

  Scenario Outline: When valid auth info & correct data (purpose, description) are POSTed to api/visitorsPurposeAdd, expect 200 status & 'Success' message in response.
    
    Given User sets "api/visitorsPurposeAdd" path param.
    Then Postrequest sent with "<key1>" and "<value1>"and "<key2>" and "<value2>" must have "<status>" and "<message>"

    Examples:
      | key1                | value1            | key2          | value2                    | status | message  |
      | visitors_purpose   | Veli Ziyareti      | description  | Veli Ziyareti İçin Gelindi | 200     | Success |

  @omer
  Scenario Outline: When invalid auth or missing data (purpose, description) is POSTed to api/visitorsPurposeAdd, expect 403 status & 'failed' message in response.

    Given User sets "api/visitorsPurposeAdd" path param.
    Then Postrequest with invalid Authorization sent with "<key1>" and "<value1>"and "<key2>" and "<value2>" must have "<status>" and "<message>"

    Examples:
      | key1                | value1            | key2         | value2                    | status   | message |
      | visitors_purpose   | Veli Ziyareti      | description  | Veli Ziyareti İçin Gelindi | 403     | failed  |