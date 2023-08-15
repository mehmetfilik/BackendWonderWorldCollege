Feature:[DB_US08] The name information for the specified id in the topic table should be updatable.

  Scenario: [DB_TC08] The name information for the specified id in the topic table should be updatable.
    Given Start Communication With WonderWorldCollege DataBase
    Then The name information for the specified id in the topic table should be updatable
    And Close the DataBase