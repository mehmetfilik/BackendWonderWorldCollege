
@111
Feature: [DB_US11] Update the fine_amount value to '200.00' for the record in the transport_feemaster table where the month value is 'October'

  Scenario: [DB_US11-->TC01]

    Given Start Communication With WonderWorldCollege DataBase
    Then  Update fine_amount to "200.00" in transport_feemaster for records with month "October"
    When Close the DataBase



