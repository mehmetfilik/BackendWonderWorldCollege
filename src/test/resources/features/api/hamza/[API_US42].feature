@apiUS42
Feature: [API_US42] Validation of api/deleteNotice Endpoint

  @apiUS42_1
  Scenario: [API_US42-->TC01] Confirm DELETE request: Verify Successful Response for Valid Authorization and Correct Data (id)
    * User sets "api/deleteNotice" path param.
    * With "Valid" Authorization is sent Delete request must id: "248", delete_id_key: "deletedId", status: 200 and message: "Success"

  @apiUS42_1
  Scenario: [API_US42-->TC02] Confirm DELETE request:Verify 403 Response for Invalid Authorization or Incorrect Data (id)

    * User sets "api/deleteNotice" path param.
    * With "inValid" Authorization is sent Delete request must id: "252", delete_id_key: "deletedId", status: 403 and message: "failed"

  @apiUS42_3,4
  Scenario: [API_US42-->TC03] Verify Deletion of Notice Record through the API

    * User sets "api/deleteNotice" path param.
    * With "Valid" Authorization is sent Delete request must id: "253", delete_id_key: "deletedId", status: 200 and message: "Success"
    Given User sets "api/getNoticeById" path param.
    Then After Notice deleting Postrequest sent with "id" must have status: 403 and message: "failed"