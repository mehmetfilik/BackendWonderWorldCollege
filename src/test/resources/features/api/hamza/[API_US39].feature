@apiUS39
Feature: [API_US39] Validation of api/getNoticeById Endpoint

  @apiUS39_1
  Scenario Outline: [API_US39-->TC01] Verify Successful Response for Valid Authorization and Correct Data (id)
    Given User sets "api/getNoticeById" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 33     | 200     | Success |

  @apiUS39_2
  Scenario Outline: [API_US39-->TC02] Verify 403 Response for Invalid Authorization or Invalid Data (id)
    Given User sets "api/getNoticeById" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 34     | 403     | failed |

  @apiUS39_3
  Scenario: [API_US39-->TC03] Validate Content of List Data in Response Body
    Given The contents of the list data with different id "33" in the NoticeList Response Body should be verified.

