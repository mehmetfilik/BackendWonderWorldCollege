@apiUS41
Feature: [API_US41] As an administrator, Validation of api/updateNotice Endpoint

  @apiUS41_1
  Scenario: [API_US41-->TC01] Verify Successful Response for Valid Authorization and Correct Data (id, type, title, description, slug)
    Given User sets "api/updateNotice" path param.
    Then In Notice List with "Valid" Authorization is sent Patch request must id: "256", update_id_key: "updateId", status: 200 and message: "Success"

    @apiUS41-2
  Scenario: [API_US41-->TC02] Verify 403 Response for Invalid Authorization or Missing/Incorrect Data (id)
      Given User sets "api/updateNotice" path param.
    Then In Notice List with "inValid" Authorization is sent Patch request must id: "399", update_id_key: "updatedId", status: 403 and message: "failed"

  @apiUS41-3,4
  Scenario: [API_US41-->TC03] Verify Update of Notice Record through the API
    Given User sets "api/updateNotice" path param.
    Then In Notice List with "Valid" Authorization is sent Patch request must id: "255", update_id_key: "updateId", status: 200 and message: "Success"
    Given User sets "api/getNoticeById" path param.
    Then After Notice List updating Postrequest sent with "id" must have status: 200 and message: "Success"
