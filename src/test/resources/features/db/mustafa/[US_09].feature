Feature:[DB_US09] A new record should be added to the transport_route table.
  Scenario: [DB_TC09] A new record should be added to the transport_route table.
    Given Start Communication With WonderWorldCollege DataBase
    Then A new record should be added to the transport_route table.
    And Close the DataBase