@logout

Feature: Log Out from QUGEM

  Background: User needs to be logged in the system
    Given the user logged in as 'admin'

  Scenario: the user should be able to logout
    When the user logged out
    Then the user should be at login page


