
@apiUS40

Feature: As an administrator, I want to create a new visitor purpose record through API connection.

  @apiUS40_1
  Scenario Outline: [API_US40-->TC_01] Verify Successful Response for Valid Authorization and Correct Data (type, title, description, slug)
    Given User sets "api/addNotice" path param.
    Then Postrequest sent with "Valid" Authorization "<type>", "<title>", "<description>", "<slug>" must have <status> and "<message>"
    Examples:
      | type           | title      | description       | slug          | status | message |
      | testtype3      | testtitle3 | testdescription3  | testslug3     | 200    | Success |

  @apiUS40_2
  Scenario Outline: [API_US40-->TC02] Verify 403 Response for Invalid Authorization or Missing Data (type, title, description, slug)
    Given User sets "api/addNotice" path param.
    Then Postrequest sent with "inValid" Authorization "<type>", "<title>", "<description>", "<slug>" must have <status> and "<message>"
    Examples:
      | type           | title      | description       | slug          | status | message |
      | testtype3      | testtitle3 | testdescription3  | testslug3     | 200    | Success |

  @apiUS40_3
  Scenario Outline: [API_US40-->TC03] Verify Creation of New Notice Record Given a new notice record is created through the API
    Given User sets "api/addNotice" path param.
    Then Postrequest sent with "Valid" Authorization "<type>", "<title>", "<description>", "<slug>" must have <status> and "<message>"
    Given User sets "api/getNoticeById" path param.
    Then In NoticeList Postrequest sent with "id" must have status: 200 and message: "Success"
    Examples:
      | type           | title      | description       | slug          | status | message |
      | testtype3      | testtitle3 | testdescription3  | testslug3     | 200    | Success |