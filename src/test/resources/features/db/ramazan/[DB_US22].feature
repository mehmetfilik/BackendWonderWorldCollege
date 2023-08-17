Feature:[DB_US22] List the name and ID of the top 10 income values from the income table based on the highest amount.


  @
  Scenario:[DB_US22]
      Given Start Communication With WonderWorldCollege DataBase
      When The top 10 individuals with the highest amount value are listed from the Income table
      Then Close the DataBase