
Feature: As an administrator, I want to access the alumni events information between the specified start date and end date through API connection.

  Background:
    Given API User sets "api/alumniEventsByDateRange" path parameters.

  Scenario Outline: [API_US08-->TC01] Confirm that using valid authorization and correct data (start,end) in a POST body to  api/alumniEventsByDateRange endpoint produce a 200 status code, with the response body's "message" being "Success."
    Then Send a Post request  with "valid authorization", "<key>" on "<value>","<key1>" on "<value1>" and must have <status> and "<message>"
    Examples:
      | key     |value                    | key1  |value1                | status | message |
      | start   |2023-08-14 00:00:00      | end   |2023-08-20 23:59:00   | 200    | Success |

  Scenario Outline: [API_US08-->TC02] Verify that sending an invalid POST body containing unauthorized credentials or invalid data (start,end) to the  api/alumniEventsByDateRange endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Then Send a Post request  with "invalid authorization", "<key>" on "<value>","<key1>" on "<value1>" and must have <status> and "<message>"
    Examples:
      | key     |value                    | key1  |value1                | status | message |
      | start   |2021-01-25 00:00:00      | end   |2023-03-25 23:59:00   | 403    | failed  |

  Scenario:[API_USO8 -->TC03] The Alumni Events By Date Range response body  should contain data with the id of "1" when start and end date Posted

    Then Confirm the content of Alumni Events By Date Range response body list data in which Id "1" when "start and "end" is sent with Posted request.
