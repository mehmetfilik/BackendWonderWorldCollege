Feature: [US_43] As an administrator (teacher), I want to access the Homework List through API connection.


    @r1
    Scenario:[API_US43-->TC01] Success Response
      Given a valid authorization is provided
      When a GET request is sent to "apiteacher/homeworkList" endpoint
      Then the response status code should be 200
      Then the response message should be "Success"

    @r2
    Scenario: [API_US43-->TC02] When Invalid Authorization is Used for GET Request to apiteacher/homeworkList, Expect 403 Status and 'failed' Message
      Given a invalid authorization is provided
      When a GET request is sent to "apiteacher/homeworkList" endpoint
      Then the response status code should be 403
      Then the response message should be "failed"

      @r3
      Scenario: [API_US43-->TC02] The content of the "lists" within the response body should be validated to conform to the specified criteria.
        Given a valid authorization is provided
        When a GET request is sent to "apiteacher/homeworkList" endpoint
        Then the content of the "lists" in the response body should match the specified data
          | id  | class_id | section_id | session_id | staff_id | subject_group_subject_id | subject_id | homework_date | submit_date | marks | description                                 | create_date | evaluation_date | document | created_by | evaluated_by | created_at            | class     | section | subject_name | subject_groups_id | name                    | assignments |
          | 151 | 1        | 1          | 18         | 5        | 21                       | 1          | 2022-07-05    | 2022-07-08  | null  | "<p>\r\n\r\nRead carefully\r\n\r\n<br></p>" | 2022-07-01  | 0000-00-00      |          | 5          | null         | 2023-01-18 01:07:13   | Class 1   | A       | English      | 4                 | Class 1st Subject Group | 0           |
