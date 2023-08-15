 Feature: [API_US028] As an administrator, I want to access the Books List through API connection.

  Scenario: [API_US028-->TC01] Valid authorization with a GET request to the api/booksList yields a 200 status code and "Success" as the response message.

    Given User sets "api/booksList" path param.
    Then With "Valid" Authorization is sent Get request must status: "200" and message: "Success"


  Scenario: [API_US028-->TC02] Invalid authorization info with a GET request to the api/booksList yields a 403 status code and "failed" response message.

    Given User sets "api/booksList" path param.
    Then With "invalid" Authorization is sent Get request must status: "403" and message: "Failed"


   Scenario: [API_US028-->TC03] Check response body data (id="1") for details like book_title,book_no,isbn_no,subject,rack_no,publish,author,qty,perunitcost,postdate,description,available,and is_active.

     Given User sets "api/booksList" path param.
     Then Then The contents of the list data with id: "1" in the BookList Response Body should be verified.













