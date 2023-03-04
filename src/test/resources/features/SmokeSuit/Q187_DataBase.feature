@MongoDB
Feature: MongoDB connection should be worked properly.


  Scenario: Should create a new MongoClient connected to MongoDB
    Then Verify the MongoClient is not null


  Scenario: Should get a database from the MongoClient
    Then Verify the database is not null


  Scenario: Should get a collection from the MongoClient
    Then Verify the collection is not null


  Scenario: Should get a employee information using id
    Then Verify the "61e58b9c321d640016b9ddc3" employee information


  Scenario: Should be true the data of each Vehicle Status table in the dashboard
    Given the user is on the login page
    When the user logged in as "admin"
    And Click the all arrow buttons
    Then Verify count data on vehicle type "Sprinter"
    Then Verify count data on vehicle type "LKW"
    Then Verify count data on vehicle type "Sattle"
    Then Verify vehicle plate and user data on "idle" vehicle type "Sprinter"
    #Then Verify vehicle plate and user data on "inUse" vehicle type "Sprinter"
    Then Verify vehicle plate and user data on "inRepair" vehicle type "Sprinter"
    Then Verify vehicle plate and user data on "idle" vehicle type "LKW"
    #Then Verify vehicle plate and user data on "inUse" vehicle type "LKW"
    Then Verify vehicle plate and user data on "inRepair" vehicle type "LKW"
    Then Verify vehicle plate and user data on "idle" vehicle type "Sattle"
    #Then Verify vehicle plate and user data on "inUse" vehicle type "Sattle"
    Then Verify vehicle plate and user data on "inRepair" vehicle type "Sattle"