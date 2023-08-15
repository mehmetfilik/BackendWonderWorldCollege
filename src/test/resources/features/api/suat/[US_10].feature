@API
Feature: As an administrator, I want to create a new Alumni Events record through API connection.

  Background:
    Given API User sets "api/alumniEventsAdd" path parameters.

  Scenario Outline: [API_US10-->TC01] Confirm that using valid authorization and correct data (title, event_for, session_id, section, from_date, to_date, note, event_notification_message, show_onwebsite) in a POST body to  api/alumniEventsAdd endpoint produce a 200 status code, with the response body's "message" being "Success."
    Then Send a Post request  with "valid authorization" and "<title>", "<event_for>","<session_id>", "<section>","<from_date>","<to_date>","<note>","<event_notification_message>" and "<show_onwebsite>" correct data  should have <status> and "<message>"
    Examples:
      | title        | event_for | session_id | section | from_date           | to_date             | note  | event_notification_message | show_onwebsite | status | message |
      | Art Activite | art       | 13         | null    | 2023-11-14 00:00:00 | 2023-11-24 23:59:00 | Paint | Art                        | 0              | 200    | Success |

  Scenario Outline: [API_US10-->TC02] Verify that sending an invalid POST body containing unauthorized credentials or invalid data (title, event_for, session_id, section, from_date, to_date, note, event_notification_message, show_onwebsite) to the  api/alumniEventsAdd endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Then Send a Post request  with "invalid authorization" and "<title>", "<event_for>","<session_id>", "<section>","<from_date>","<to_date>","<note>","<event_notification_message>" and "<show_onwebsite>" correct data  should have <status> and "<message>"
    Examples:
      | title        | event_for | session_id | section | from_date           | to_date             | note  | event_notification_message | show_onwebsite | status | message |
      | Art Activite | all       | 15         | null    | 2023-11-14 00:00:00 | 2023-11-24 23:59:00 | Paint | Art                        | 0              | 403    | failed |

  Scenario Outline: [API_US10-->TC03] Verify that a new recors has been created by sending Post request to api/alumniEventsId endpoint
    Then Generate a new record with "<id>", "<title>", "<event_for>", "<session_id>","<note>" in Alumni Events Id and correct data should have "<status>" and "<message>"

    Examples:
     |  id       | title          | event_for | session_id | note  | status | message |
     |  1071     | Sport Activite | all       | 18         | Paint | 200    | Success |
