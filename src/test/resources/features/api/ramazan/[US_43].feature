Feature: [US_43] As an administrator (teacher), I want to access the Homework List through API connection.


      @a2
      Scenario:[API_US43-->TC01] Success Response
        Given a valid authorization is provided
        When a GET request is sent to "apiteacher/homeworkList" endpoint
        Then the response status code should be 200
        Then the response message should be "Success"


      @a3
      Scenario: [API_US43-->TC02] When Invalid Authorization is Used for GET Request to apiteacher/homeworkList, Expect 403 Status and 'failed' Message
        Given GET request is sent to "apiteacher/homeworkList" endpoint with invalid authorization
        Then the response status code should be 403
        Then the response message should be "failed"


      Scenario Outline: [API_US43-->TC03] The content of the "lists" within the response body should be validated to conform to the specified criteria.
        Given a valid authorization is provided
        When a PUT request is sent to "apiteacher/homeworkbyId" endpoint
        Then the content of the response except for "id" should match the specified data
        Examples:
          | class_id | section_id | session_id | staff_id | subject_group_subject_id | subject_id | homework_date | submit_date | marks    | description              | create_date | evaluation_date | document | created_by | evaluated_by | created_at             | subject_name | subject_groups_id | name                    | assignments |
          | 3        | 1          | 18         | 155      | 41                       | 7          | 2023-08-03    | 2023-08-03  | 100.00   | "<p>French Study!!!</p>" | 2023-08-03  | null            |          | 155        | null         | 2023-08-03 08:49:36    | French       | 6                 | Class 3rd Subject Group | 0           |

