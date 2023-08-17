@demoAPI
Feature: It is used to list all the countries registered in the database.

  Scenario: [US_01-->TC_01]Success Response
    Given User sets "api/visitorsPurposeList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [US_01-->TC_02]Failed Response
    Given User sets "api/visitorsPurposeList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario: [US_01-->TC_03]The content of the lists in the response body should be validated to contain data with ID "1," where the visitors_purpose is "Marketing," and created_at is "2023-01-18 01:07:12."

    Then The contents of the list data with id: "2" in the VisitorPurpose Response Body should be verified.

  Scenario Outline: [API_US02-->TC01,TC_03] When valid authorization info and correct ID data are POSTed to api/visitorsPurposeId, expect 200 status with 'Success' message in response.

    Given User sets "api/visitorsPurposeId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 2     | 200     | Success |

  Scenario Outline: [API_US02-->TC02] When invalid auth or data (ID) is POSTed to api/visitorsPurposeId, expect 403 status with 'failed' message in response.

    Given User sets "api/visitorsPurposeId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 6     | 403     | failed |

  Scenario: [API_US02-->TC03] The content of the list data (ID, visitors_purpose, description, created_at) in the response body should be validated.

    Given The contents of the list data with id: "2" in the VisitorPurpose Response Body should be verified.


  Scenario Outline: [API_US03] When valid auth info & correct data (purpose, description) are POSTed to api/visitorsPurposeAdd, expect 200 status & 'Success' message in response.

    Given User sets "api/visitorsPurposeAdd" path param.
    Then Postrequest sent with "<key1>" and "<value1>"and "<key2>" and "<value2>" must have "<status>" and "<message>"

    Examples:
      | key1               | value1             | key2         | value2                     | status | message  |
      | visitors_purpose   | Veli Ziyareti      | description  | Veli Ziyareti İçin Gelindi | 200     | Success |


  Scenario Outline: [API_US03-->TC_02] When invalid auth or missing data (purpose, description) is POSTed to api/visitorsPurposeAdd, expect 403 status & 'failed' message in response.

    Given User sets "api/visitorsPurposeAdd" path param.
    Then Postrequest with invalid Authorization sent with "<key1>" and "<value1>"and "<key2>" and "<value2>" must have "<status>" and "<message>"

    Examples:
      | key1                | value1            | key2         | value2                    | status   | message |
      | visitors_purpose   | Veli Ziyareti      | description  | Veli Ziyareti İçin Gelindi | 403     | failed  |


  #Scenario Outline: [API_US04-->TC_01, TC_03] Valid authorization info, correct data (id, visitors_purpose, description) sent in PATCH body to api/visitorsPurposeUpdate yields status 200 with response message "Success."

   # Given User sets "api/visitorsPurposeUpdate" path param.
    #Then Patchrequest sent with "<key1>" , "<value1>", "<key2>" and "<value2>","<key3>" and "<value3>" and must have "<status>" and "<message>"

    #Examples:
     # | key1 | value1 | key2              | value2         | key3            | value3                 |status | message  |
     # | id   | 10     | visitors_purpose  | purpose update | description     | came for student visit |200    | Success  |


  Scenario Outline: [API_US04-->TC_02] Invalid auth or missing/wrong data (id) in PATCH body (with visitors_purpose, description) to api/visitorsPurposeUpdate results in status 403 with response message "failed.

    Given User sets "api/visitorsPurposeUpdate" path param.
    Then Patchrequest sent with invalid Authorization with "<key1>" , "<value1>", "<key2>", "<value2>","<key3>" and "<value3>" and must have "<status>" and "<message>"

    Examples:
      | key1 | value1 | key2              | value2         | key3            | value3                 |status | message |
      | id   | 1001   | visitors_purpose  | purpose update | description     | came for student visit |403    | failed  |

#  Scenario: [API_US4-->TC04] Validate successful API update of visitor purpose record by confirming with the returned updateId in response body for a POST request to api/visitorsPurposeId endpoint to verify updated record.

#    Given Checking the id updated with Postrequest id: 10 from API


  Scenario: [API_US05-->TC01,TC03] When valid authorization information and correct data (id) are sent in the DELETE body to the api/visitorsPurposeDelete endpoint, the expected status code is 200, and the message in the response body should be "Success.

    Given Creating a new record with Post Request and deleting it with deleterequest.


  Scenario:  [API_US05-->TC02] When invalid authorization information or wrong data (id) is sent in the DELETE body to the api/visitorsPurposeDelete endpoint, the expected status code is 403, and the message in the response body should be "failed.

    Given Creating a new record with postrequest and verifying that it has not been deleted with invalid authorization


  Scenario: [API_US05-->TC03] The successful deletion of the visitor purpose record via the API should be validated.

    Given Checking for a deleted record


  Scenario:[API_USO6 -->TC01] Success response

    Given User sets "api/sessionList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario:[API_USO6 -->TC02] Failed response

    Given User sets "api/sessionList" path param.
    Then With "Invalid" Authorization is sent Get request must status: "403" and message: "failed"

  Scenario:[API_USO6 -->TC03] The Session List response body should contain data with the id of "11"

    Then Confirm the content of Session List response body  data with Id "11".


  Scenario:[API_USO7 -->TC01] Success response

    Given User sets "api/alumniEventsList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario:[API_USO7 -->TC02] Failed response

    Given User sets "api/alumniEventsList" path param.
    Then With "Invalid" Authorization is sent Get request must status: "403" and message: "failed"
  Scenario:[API_USO7 -->TC03] The Alumni Event List response body  should contain data with the id of "2"

    Then Confirm the content of Alumni Event List response body list data with Id "2".



  Scenario Outline: [API_US08-->TC01] Confirm that using valid authorization and correct data (start,end) in a POST body to  api/alumniEventsByDateRange endpoint produce a 200 status code, with the response body's "message" being "Success."
    Given User sets "api/alumniEventsByDateRange" path param.
    Then Send a Post request  with "valid authorization", "<key>" on "<value>","<key1>" on "<value1>" and must have <status> and "<message>"
    Examples:
      | key     |value                    | key1  |value1                | status | message |
      | start   |2023-08-14 00:00:00      | end   |2023-08-20 23:59:00   | 200    | Success |

  Scenario Outline: [API_US08-->TC02] Verify that sending an invalid POST body containing unauthorized credentials or invalid data (start,end) to the  api/alumniEventsByDateRange endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Given User sets "api/alumniEventsByDateRange" path param.
    Then Send a Post request  with "invalid authorization", "<key>" on "<value>","<key1>" on "<value1>" and must have <status> and "<message>"
    Examples:
      | key     |value                    | key1  |value1                | status | message |
      | start   |2021-01-25 00:00:00      | end   |2023-03-25 23:59:00   | 403    | failed  |

  Scenario:[API_USO8 -->TC03] The Alumni Events By Date Range response body  should contain data with the id of "1" when start and end date Posted
    Given User sets "api/alumniEventsByDateRange" path param.
    Then Confirm the content of Alumni Events By Date Range response body list data in which Id "1" when "start and "end" is sent with Posted request.



  Scenario Outline: [API_US09-->TC01] Confirm that using valid authorization and correct data (id) in a POST body to  api/alumniEventsId endpoint produce a 200 status code, with the response body's "message" being "Success."
    Given User sets "api/alumniEventsId" path param.
    Then Send a Post request  with "valid authorization" "<key>" and <value> should have "<status>" and "<message>"
    Examples:
      | key  | value | status | message |
      | id   | 930     | 200    | Success |

  Scenario Outline: [API_US09-->TC02] Verify that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the  api/alumniEventsId endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Given User sets "api/alumniEventsId" path param.
    Then Send a Post request  with "invalid authorization" "<key>" and <value> should have "<status>" and "<message>"
    Examples:
      | key  | value | status | message |
      | id   | 5     | 403    | failed  |

  Scenario: [API_US09-->TC03] Validate content of Alumni Events information by ID in the response body

    Given User sets "api/alumniEventsId" path param.
    Then The content of the list data should be validated with id:"930" in the Alumni Events



  Scenario Outline: [API_US10-->TC01] Confirm that using valid authorization and correct data (title, event_for, session_id, section, from_date, to_date, note, event_notification_message, show_onwebsite) in a POST body to  api/alumniEventsAdd endpoint produce a 200 status code, with the response body's "message" being "Success."
    Given User sets "api/alumniEventsAdd" path param.
    Then Send a Post request  with "valid authorization" and "<title>", "<event_for>","<session_id>", "<section>","<from_date>","<to_date>","<note>","<event_notification_message>" and "<show_onwebsite>" correct data  should have <status> and "<message>"
    Examples:
      | title        | event_for | session_id | section | from_date           | to_date             | note  | event_notification_message | show_onwebsite | status | message |
      | Art Activite | art       | 13         | null    | 2023-11-14 00:00:00 | 2023-11-24 23:59:00 | Paint | Art                        | 0              | 200    | Success |

  Scenario Outline: [API_US10-->TC02] Verify that sending an invalid POST body containing unauthorized credentials or invalid data (title, event_for, session_id, section, from_date, to_date, note, event_notification_message, show_onwebsite) to the  api/alumniEventsAdd endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Given User sets "api/alumniEventsAdd" path param.
    Then Send a Post request  with "invalid authorization" and "<title>", "<event_for>","<session_id>", "<section>","<from_date>","<to_date>","<note>","<event_notification_message>" and "<show_onwebsite>" correct data  should have <status> and "<message>"
    Examples:
      | title        | event_for | session_id | section | from_date           | to_date             | note  | event_notification_message | show_onwebsite | status | message |
      | Art Activite | all       | 15         | null    | 2023-11-14 00:00:00 | 2023-11-24 23:59:00 | Paint | Art                        | 0              | 403    | failed |

  Scenario Outline: [API_US10-->TC03] Verify that a new recors has been created by sending Post request to api/alumniEventsId endpoint
    Given User sets "api/alumniEventsAdd" path param.
    Then Generate a new record with "<id>", "<title>", "<event_for>", "<session_id>","<note>" in Alumni Events Id and correct data should have "<status>" and "<message>"

    Examples:
      |  id       | title          | event_for | session_id | note  | status | message |
      |  1071     | Sport Activite | all       | 18         | Paint | 200    | Success |


#  Scenario:[US_13-->TC_01] Using valid authorization for GET request to api/vehicleList should return status 200 with 'Success' message in response
#    Given User sets "api/vehicleList" path param.
#    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"
#
#
#  Scenario:[US_13-->TC_02] Failed Response
#    Given User sets "api/vehicleList" path param.
#    Then When a Get request is made with invalid Authorization, the status must be 403, and the message must be failed.
#
#
#  Scenario: [US_13-->TC_03] The lists content in the response body should be able to verify the data content with id=1
#    Given User sets "api/vehicleList" path param.
#    Then The contents of the list data with id: "1" in the vehicleList Response Body should be verified.




  Scenario Outline: [API_US14-->TC01] Confirm that using valid authorization and correct data (id) in a POST body to api/vehicleId endpoint yields a 200 status code, with the response body's "message" being "Success."
    Given User sets "api/vehicleId" path param
    Then Post request sent with "valid authorization" "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 1     | 200    | Success |

  Scenario Outline: [API_US14-->TC02] Validate that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the api/vehicleId endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Given User sets "api/vehicleId" path param
    Then Post request sent with "invalid authorization" "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 1     | 403    | failed  |


  Scenario: [API_US14-->TC03] The lists content in the response body should be able to verify the data content with id=1.
    Given User sets "api/vehicleId" path param
    Given Verify the data content with lists content id=1 in the response body.


  Scenario:[US_15-->TC_01] When valid authorization info and correct data are POSTed to api/vehicleAdd, expected status code: 200, and response body message should be 'Success'.
    Given User sets "api/vehicleAdd" path param.
    Then User posts valid authorization info and correct data to api.vehicleAdd, expecting status code 200 and confirming response body "message" as "Success".


  Scenario: [API_US15-->TC02] Validate that sending an invalid POST body containing unauthorized credentials or invalid data (id) to the api/vehicleAdd endpoint results in a 403 status code, and the response body's "message" should be verified as "failed."
    Given User sets "api/vehicleAdd" path param.
    Then When invalid auth or incomplete data is sent to api.vehicleAdd, confirm status code 403 and response "message" as "failed".

  Scenario:[API_US15-->TC03] The new vehicle record that is desired to be created via the API must be verified through the API where it is created.
    Given Create a new vehicle registration via API


#  Scenario:[US_16-->TC_01] Valid authorization and correct data in PATCH request to api.vehicleUpdate should return 200 status and Success message.
#    Given User sets "api/vehicleUpdate" path param.
#    Then send endpoint valid PATCH body and verify that the returned status code is 200 and the "message" information is "Success"
#
#  Scenario: [US_16-->TC_02] Valid authorization and correct data in PATCH request to api.vehicleUpdate should return 200 status and Success message.
#    Given User sets "api/vehicleUpdate" path param.
#    Then send endpoint invalid PATCH body and verify that the returned status code is 403 and the "message" information is "failed"
#
#  Scenario:[US_16-->TC_03] The response body's updateId must match the id sent in the PATCH request body to the api.vehicleUpdate endpoint.
#    Given User sets "api/vehicleUpdate" path param.
#    Then The updateId in the response must match the id in the PATCH request body sent to the api.vehicleUpdate endpoint.
#
#  Scenario: [US_16-->TC_03] The successful update of the desired vehicle record via the API should be validated.
#    Given User sets "api/vehicleUpdate" path param.
#    Then Update the vehicle registration with ID 28 and verify that it has been updated

  Scenario: [US_17-->TC_01] Valid authorization, correct ID in DELETE request to api/vehicleDelete should return 200 status, 'Success' message.
    Given User sets "api/vehicleDelete" path param.
    Then send endpoint valid DELETE body and verify that the returned status code is 200 and the "message" information is "Success"

  Scenario:[US_17-->TC02] Failed Response
    Given User sets "api/vehicleDelete" path param.
    Then When invalid auth or incomplete data is sent to api.vehicleDelete, confirm status code 403 and response "message" as "failed".

#  Scenario: [US_17-->TC_03] It should be verified that the Deleted information in the response body is the same as the id information in the request body.
#    Given User sets "api/vehicleDelete" path param.
#    Then It is verified that the Deleted information in the response body is the same as the id in the request body.

 Scenario: [US_17-->TC04] It should be verified via API that the vehicle record to be deleted via API is deleted.
   Given User sets "api/vehicleId" path param.
   Then It is verified via the API that the vehicle record to be deleted via the API is deleted.


  Scenario: [API_US22-->TC01] Success Response

    Given User sets "api/alumniList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US22-->TC02] Failed Response

    Given User sets "api/alumniList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


#  Scenario: [API_US22-->TC03] The content of the lists in the response body should contain the following data: (id: "2", student_id: "41", current_email: "rohan@gmail.com", current_phone: "0808080707", occupation:"", address: "", photo:null, created_at: "2023-03-11 03:04:50").This should be verified to ensure the correct response data.
#
#    Given User sets "api/alumniId" path param.
#    Then The contents of the list data with id: "2" in the Alumni Response Body should be verified.


  Scenario: [API_US22-->TC01] When a valid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Given User sets "api/studentList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US22-->TC02]  When invalid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Given User sets "api/studentList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario: [API_US22-->TC03] Response Data Validation for class id "3"
    Given User sets "api/alumniId" path param.
    Then The contents of the list data with id: "3" in the AlumniStudent Response Body should be verified.




  Scenario Outline: [API_US24-->TC01] When a valid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Given User sets "api/alumniId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 2     | 200    | Success |


  Scenario Outline: [API_US24-->TC02] When invalid authorization information is sent in a GET request to the api/studentList endpoint, the expected status code is 403, and the message in the response body should be "failed."
    Given User sets "api/alumniId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 1  | 403    | failed  |


  Scenario: [API_US24-->TC03] The new alumni record should be created on the API, and this can be verified by sending the addId from the response body to the api/alumniId endpoint in a POST body to retrieve the newly created alumni record.
    Given User sets "api/alumniId" path param.
    Then The contents of the list data with id: "2" in the AlumniId Response Body should be verified.


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


  Scenario: [API_US26-->TC01] When valid authorization information and the correct data (id, student_id, current_email, current_phone, occupation, address, photo) are sent in a PATCH body to the api/alumniUpdate endpoint, the expected status code is 200, and the message in the response body should be "Success."

    Given User sets "api/alumniUpdate" path param.
    Then With "valid" Authorization is sent Patch request must id: "29", update_id_key: "updateId", status: 200 and message: "Success"



  Scenario: [API_US26-->TC02] When invalid authorization information or missing/incorrect data (id) is sent in a PATCH body (student_id, current_email, current_phone, occupation, address, photo) to the api/alumniUpdate endpoint, the expected status code is 403, and the message in the response body should be "failed."

    Given User sets "api/alumniUpdate" path param.
    Then With "invalid" Authorization is sent Patch request must id: "29", update_id_key: "updateId", status: 403 and message: "failed"



  Scenario: [API_US26-->TC03] The updateId in the response body should match the id sent in the PATCH request body to the api/alumniUpdate endpoint.

    Given User sets "api/alumniUpdate" path param.
    Then With "valid" Authorization is sent Patch request must id: "29", update_id_key: "updateId", status: 200 and message: "Success"
    Given User sets "api/alumniId" path param.
    Then After Alumni updating Postrequest sent with "id" must have status: 200 and message: "Success"


  Scenario: [API_US028-->TC01] Valid authorization with a GET request to the api/booksList yields a 200 status code and "Success" as the response message.

    Given User sets "api/booksList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US028-->TC02] Invalid authorization info with a GET request to the api/booksList yields a 403 status code and "failed" response message.

    Given User sets "api/booksList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "failed"


  Scenario: [API_US028-->TC03] Check response body data (id="1") for details like book_title,book_no,isbn_no,subject,rack_no,publish,author,qty,perunitcost,postdate,description,available,and is_active.

    Given User sets "api/booksList" path param.
    Then Then The contents of the list data with id: "1" in the BookList Response Body should be verified.




  Scenario Outline: [API_US029-->TC01] API/booksId POST request with valid auth and id returns 200 "Success".
    Given User sets "api/booksId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 1    | 200    | Success |


  Scenario Outline: [API_US029-->TC02] Invalid auth or id in api/booksId POST returns 403 "failed".
    Given User sets "api/booksId" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"

    Examples:
      | key | value | status | message |
      | id  | 151   |   403  |  failed |



  Scenario: [API_US029-->TC03] Response body's list content must include data for id, book_title, book_no, etc., 11 attributes in total.
    Given User sets "api/booksId" path param.
    Then Then The contents of the list data with id: "1" in the BookList Response Body should be verified.


  Scenario Outline: [API_US30-->TC01] Valid auth info and correct book data sent via POST to api/booksAdd returns a 200 status code with "Success" message.

    Given User sets "api/booksAdd" path param.
    Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"

    Examples:

      | book_title                                | book_no   | isbn_no   | subject      | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    |    001    |   sassd      | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |


  Scenario Outline: [API_US30-->TC02] Invalid auth info or incomplete data (book info) in POST to api/booksAdd yields expected 403 status code with response message "failed."

    Given User sets "api/booksAdd" path param.
    Then Postrequest with invalid Authorization sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"

    Examples:

      | book_title                                | book_no   | isbn_no   | subject | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    |  001      |  sassd  | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 403    | failed  |


  Scenario Outline: [API_US30-->TC03] The new books record that is intended to be created through the API should be verified through the API.

    Given User sets "api/booksAdd" path param.
    Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"
    Given User sets "api/booksAdd" path param.
    Then in Books Postrequest sent with "id" must have status: 200 and message: "Success"
    Examples:
      | book_title                                | book_no   | isbn_no   | subject | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    | 001       | sassd   | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |



  Scenario: [API_US31-->TC01] Valid auth info and correct data (book info) sent via PATCH to api/booksUpdate results in expected 200 status code with response message "Success.

    Given User sets "api/booksUpdate" path param.
    Then in books with "Valid" Authorization is sent Patch request must id: "85", update_id_key: "updateId", status: 200 and message: "Success"



  Scenario: [API_US31-->TC02] invalid auth or incomplete/incorrect data in PATCH to api/booksUpdate leads to expected 403 status with response message "failed."

    Given User sets "api/booksUpdate" path param.
    Then in books with "inValid" Authorization is sent Patch request must id: "85", update_id_key: "updateId", status: 403 and message: "failed"

  Scenario: [API_US31-->TC04] To further validate that the book record was updated, we can send a POST body to the api/booksId endpoint using the returned updateId to check the updated details of the book.

    Given User sets "api/booksUpdate" path param.

    Then in books with "Valid" Authorization is sent Patch request must id: "87", update_id_key: "updateId", status: 200 and message: "Success"

    Given User sets "api/booksId" path param.
    Then After Books updating Postrequest sent with "id" must have status: 200 and message: "Success"


  Scenario Outline: [API_US32-->TC01] Valid auth info and correct data (id) sent via DELETE to api/booksDelete results in expected 200 status code with response message "Success."

    Given User sets "api/booksAdd" path param.
    Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"
    * User sets "api/booksDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "366", delete_id_key: "DeletedId", status: 200 and message: "Success"

    Examples:

      | book_title                                | book_no   | isbn_no   | subject      | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    |    001    |   sassd      | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |




  Scenario: [API_US32-->TC02]

    * User sets "api/booksDelete" path param.
    * With "inValid" Authorization is sent Delete request must id: "473", delete_id_key: "DeletedId", status: 403 and message: "failed"


  Scenario Outline: [API_US32-->TC03]

    Given User sets "api/booksAdd" path param.
    Then Postrequest sent with "<book_title>","<book_no>","<isbn_no>","<subject>","<rack_no>","<publish>","<author>","<qty>","<perunitcost>","<postdate>","<description>",must have "<status>" and "<message>"
    * User sets "api/booksDelete" path param.
    * With "Valid" Authorization is sent Delete request must id: "367", delete_id_key: "DeletedId", status: 200 and message: "Success"
    Given User sets "api/booksId" path param.
    Then After Books deleting Postrequest sent with "id" must have status: 403 and message: "failed"

    Examples:

      | book_title                                | book_no   | isbn_no   | subject      | rack_no | publish         | author        | qty | perunitcost | postdate   | description                                                                                                                                      | status | message |
      | Multiplication and Division Grades 3-4 23 | 788789    |    001    |   sassd      | 110     | Barbara Bando   | Barbara Bando | 100 | 12.00       | 2022-05-04 | The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.      | 200    | Success |




  Scenario: [US_38-->TC_01]Successful GET Request to api/getNotice with Valid Authorization
    Given User sets "api/getNotice" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario:[US_38-->TC_02] Failed Response
    Given User sets "api/getNotice" path param.
    Then When a Get request is made with invalid Authorization, the status must be 403, and the message must be failed.


#  Scenario: [US_38-->TC_03] The lists content in the response body should be able to verify the data content with id=1
#
#    Then The contents of the list data with id: "31" in the Notice List Response Body should be verified.


  Scenario Outline: [API_US39-->TC01] Verify Successful Response for Valid Authorization and Correct Data (id)
    Given User sets "api/getNoticeById" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 31     | 200     | Success |


  Scenario Outline: [API_US39-->TC02] Verify 403 Response for Invalid Authorization or Invalid Data (id)
    Given User sets "api/getNoticeById" path param.
    Then Postrequest sent with "<key>" and "<value>" must have "<status>" and "<message>"
    Examples:
      | key | value | status | message |
      | id  | 34     | 403     | failed |


#  Scenario: [API_US39-->TC03] Validate Content of List Data in Response Body
#    Given The contents of the list data with different id "31" in the NoticeList Response Body should be verified.

  Scenario Outline: [API_US40-->TC_01] Verify Successful Response for Valid Authorization and Correct Data (type, title, description, slug)
    Given User sets "api/addNotice" path param.
    Then Postrequest sent with "Valid" Authorization "<type>", "<title>", "<description>", "<slug>" must have <status> and "<message>"
    Examples:
      | type           | title      | description       | slug          | status | message |
      | testtype3      | testtitle3 | testdescription3  | testslug3     | 200    | Success |


# Scenario Outline: [API_US40-->TC02] Verify 403 Response for Invalid Authorization or Missing Data (type, title, description, slug)
#   Given User sets "api/addNotice" path param.
#   Then Postrequest sent with "inValid" Authorization "<type>", "<title>", "<description>", "<slug>" must have <status> and "<message>"
#   Examples:
#     | type           | title      | description       | slug          | status | message |
#     | testtype3      | testtitle3 | testdescription3  | testslug3     | 200    | Success |


  Scenario Outline: [API_US40-->TC03] Verify Creation of New Notice Record Given a new notice record is created through the API
    Given User sets "api/addNotice" path param.
    Then Postrequest sent with "Valid" Authorization "<type>", "<title>", "<description>", "<slug>" must have <status> and "<message>"
    Given User sets "api/getNoticeById" path param.
    Then In NoticeList Postrequest sent with "id" must have status: 200 and message: "Success"
    Examples:
      | type           | title      | description       | slug          | status | message |
      | testtype3      | testtitle3 | testdescription3  | testslug3     | 200    | Success |


  Scenario: [API_US41-->TC01] Verify Successful Response for Valid Authorization and Correct Data (id, type, title, description, slug)
    Given User sets "api/updateNotice" path param.
    Then In Notice List with "Valid" Authorization is sent Patch request must id: "256", update_id_key: "updateId", status: 200 and message: "Success"


  Scenario: [API_US41-->TC02] Verify 403 Response for Invalid Authorization or Missing/Incorrect Data (id)
    Given User sets "api/updateNotice" path param.
    Then In Notice List with "inValid" Authorization is sent Patch request must id: "399", update_id_key: "updatedId", status: 403 and message: "failed"


  Scenario: [API_US41-->TC03] Verify Update of Notice Record through the API
    Given User sets "api/updateNotice" path param.
    Then In Notice List with "Valid" Authorization is sent Patch request must id: "255", update_id_key: "updateId", status: 200 and message: "Success"
    Given User sets "api/getNoticeById" path param.
    Then After Notice List updating Postrequest sent with "id" must have status: 200 and message: "Success"

#  Scenario: [API_US42-->TC01] Confirm DELETE request: Verify Successful Response for Valid Authorization and Correct Data (id)
#    * User sets "api/deleteNotice" path param.
#    * With "Valid" Authorization is sent Delete request must id: "248", delete_id_key: "deletedId", status: 200 and message: "Success"


  Scenario: [API_US42-->TC02] Confirm DELETE request:Verify 403 Response for Invalid Authorization or Incorrect Data (id)

    * User sets "api/deleteNotice" path param.
    * With "inValid" Authorization is sent Delete request must id: "252", delete_id_key: "deletedId", status: 403 and message: "failed"


#  Scenario: [API_US42-->TC03] Verify Deletion of Notice Record through the API
#
#    * User sets "api/deleteNotice" path param.
#    * With "Valid" Authorization is sent Delete request must id: "253", delete_id_key: "deletedId", status: 200 and message: "Success"
#    Given User sets "api/getNoticeById" path param.
#    Then After Notice deleting Postrequest sent with "id" must have status: 403 and message: "failed"


  Scenario:[API_US43-->TC01] Success Response
    Given a valid authorization is provided
    When a GET request is sent to "apiteacher/homeworkList" endpoint
    Then the response status code should be 200
    Then the response message should be "Success"



  Scenario: [API_US43-->TC02] When Invalid Authorization is Used for GET Request to apiteacher/homeworkList, Expect 403 Status and 'failed' Message
    Given GET request is sent to "apiteacher/homeworkList" endpoint with invalid authorization
    Then the response status code should be 403
    Then the response message should be "failed"


# Scenario Outline: [API_US43-->TC03] The content of the "lists" within the response body should be validated to conform to the specified criteria.
#   Given a valid authorization is provided
#   When a PUT request is sent to "apiteacher/homeworkbyId" endpoint
#   Then the content of the response except for "id" should match the specified data
#   Examples:
#     | class_id | section_id | session_id | staff_id | subject_group_subject_id | subject_id | homework_date | submit_date | marks    | description              | create_date | evaluation_date | document | created_by | evaluated_by | created_at             | subject_name | subject_groups_id | name                    | assignments |
#     | 3        | 1          | 18         | 155      | 41                       | 7          | 2023-08-03    | 2023-08-03  | 100.00   | "<p>French Study!!!</p>" | 2023-08-03  | null            |          | 155        | null         | 2023-08-03 08:49:36    | French       | 6                 | Class 3rd Subject Group | 0           |


#  Scenario:[API_US44-->TC01] Success Response
#    Given a valid authorization is provided
#    When a POST request is sent to "apiteacher/homeworkbyId" endpoint
#    Then the response status code should be 200
#    Then the response message should be "Success"


  Scenario: [API_US44-->TC02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkListbyld, Expect 403 Status and 'failed' Message
    Given POST request is sent to "apiteacher/homeworkbyId" endpoint with invalid authorization
    Then the response status code should be 403
    Then the response message should be "failed"


#  Scenario: [API_US44-->TC03] The content of the "list" data in the response body should be verified.
#    Given a valid authorization is provided
#    When a POST request is sent to "apiteacher/homeworkbyId" endpoint
#    Then The contents of the list data within the response body are verified.


  Scenario:[API_US45-->TC01] Success Response
    Given a valid authorization is provided
    When a POST request body is sent to "apiteacher/homeworkAdd" endpoint
    Then the response status code should be 200
    Then the response message should be "Success"


  Scenario: [API_US45-->TC02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkAdd, Expect 403 Status and 'failed' Message
    Given POST request is sent to "apiteacher/homeworkAdd" endpoint with invalid authorization
    Then the response status code should be 403
    Then the response message should be "failed"


  Scenario: [API_US45-->TC03] The newly created homework record should be verified via the API.
    Given a valid authorization is provided
    When a POST request body is sent to "apiteacher/homeworkAdd" endpoint
    Then The validity of a created record is verified through the API


#  Scenario: [API_US46-->TC_01] Success response
#    Given a valid authorization is provided
#    When a PATCH request is sent to "apiteacher/homeworkUpdate" endpoint
#    Then the response status code should be 200
#    Then the response message should be "Success"

  Scenario: [API_US46-->TC_02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkUpdate, Expect 403 Status and 'failed' Message
    Given PATCH request is sent to "apiteacher/homeworkUpdate" endpoint with invalid authorization
    Then the response status code should be 403
    Then the response message should be "failed"

#  Scenario: [API_US046-->TC_03]The "updateId" in the response body should match the "id" in the PATCH request body sent to apiteacher/homeworkUpdate.
#    Given a valid authorization is provided
#    When a PATCH request is sent to "apiteacher/homeworkUpdate" endpoint
#    Then Successful verification of the update record is confirmed.
#
#  Scenario: [API_US046-->TC_04] The updated homework record should be confirmed via the API.
#    Given a valid authorization is provided
#    When a PATCH request is sent to "apiteacher/homeworkUpdate" endpoint
#    Then The update of the homework entry to be modified through the API is performed, and its confirmation is validated via the API


  Scenario:[US_47-->TC_01] Success response
    Given a valid authorization is provided
    When a DELETE request body is sent to "apiteacher/homeworkDelete" endpoint
    Then the response status code should be 200
    Then the response message should be "Success"


  Scenario:[US_47-->TC_02] When Invalid Authorization is Used for POST Request to apiteacher/homeworkDelete, Expect 403 Status and 'failed' Message
    Given DELETE request is sent to "apiteacher/homeworkDelete" endpoint with invalid authorization
    Then the response status code should be 403
    Then the response message should be "failed"



  Scenario:[US_47-->TC_03_04]The "DeletedId" in the response body should match the "id" in the DELETE request body sent to apiteacher/homeworkDelete.
    Given a valid authorization is provided
    When a DELETE request body is sent to "apiteacher/homeworkDelete" endpoint
    Then Verify DeletedId in response body matches id in DELETE request body to endpoint.
    Then The deletion of the record is confirmed through the API






