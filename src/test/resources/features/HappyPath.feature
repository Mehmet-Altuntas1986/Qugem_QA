@happy

Feature: Happy Path

  Background: User needs to be logged in the system
    Given the user logged in as 'admin'

  Scenario:
    # vehicle creation
    And the user navigates to "auto"
    And the user clicks add auto button
    And the user fills the form
    When the user clicks the save button
    Then the user should be able to see new auto information

      #Attendance Update & Verify
    When the user navigates to 'attendance'
    And the user clicks to edit button of Employee '33434'
    And  the user edit attendance for 7 day as 'S'
    Then verify attendance update

  # emp creation
    When admin navigates to 'employee'
    And the user clicks on the  create new employee button
    And the user fills the form for newEmployee





   # logout
    When the user logged out
