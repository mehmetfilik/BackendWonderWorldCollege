@filik
Feature: [API_US35] As an administrator, I want to create a new Visitor record through API connection.


  Scenario Outline: [API_US35-->TC01] When sending a valid POST request to 'api/visitorsAdd' with proper authorization and correct data, ensure that the response has a 200 status code and the message says "Success".

    Given User sets "api/visitorsAdd" path param.
    Then In Visitors with "Valid" Authorization sent Post request with "<purpose>", "<name>", "<contact>", "<id_proof>", "<no_of_people>", "<date>", "<in_time>", "<out_time>", "<note>" must have <status> and "<message>"
    Examples:
      | purpose           | name  | contact       | id_proof | no_of_people | date       | in_time  | out_time | note        | status | message |
      | Principal Meeting | Kenan | 9808678686112 | 312121   | 16           | 2023-03-16 | 06:00 PM | 06:30 PM | PTM meeting | 200    | Success |


  Scenario Outline: [API_US35-->TC02] Confirm that sending an incomplete POST body or unauthorized credentials to the 'api/visitorsAdd' endpoint results in a 403 status code and a "failed" message in the response.

    Given User sets "api/visitorsAdd" path param.
    Then In Visitors with "inValid" Authorization sent Post request with "<purpose>", "<name>", "<contact>", "<id_proof>", "<no_of_people>", "<date>", "<in_time>", "<out_time>", "<note>" must have <status> and "<message>"
    Examples:
      | purpose           | name  | contact       | id_proof | no_of_people | date       | in_time  | out_time | note        | status | message |
      | Principal Meeting | Kenan | 9808678686112 | 312121   | 16           | 2023-03-16 | 06:00 PM | 06:30 PM | PTM meeting | 403    | failed  |

  Scenario Outline: [API_US35-->TC03] Confirm the API-made visitor record. Use the addId from response in POST to 'api/visitorsId' to verify successful creation and retrieve details. This validates the creation status.

    Given User sets "api/visitorsAdd" path param.
    Then In Visitors with "Valid" Authorization sent Post request with "<purpose>", "<name>", "<contact>", "<id_proof>", "<no_of_people>", "<date>", "<in_time>", "<out_time>", "<note>" must have <status> and "<message>"
    Given User sets "api/visitorsId" path param.
    Then In Visitors Postrequest sent with "id" must have status: 200 and message: "Success"
    Examples:
      | purpose           | name  | contact       | id_proof | no_of_people | date       | in_time  | out_time | note        | status | message |
      | Principal Meeting | Kenan | 9808678686112 | 312121   | 16           | 2023-03-16 | 06:00 PM | 06:30 PM | PTM meeting | 200    | Success |



    