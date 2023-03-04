Feature: Admin should be able to add new user

  Background: precondition
    Given the user logged in as 'admin'

  @TEST_QUGEM-24
  Scenario: Admin should be able to add new user
    When admin navigates to 'adminUsers'
    And  Admin clicks addNewUser button
    And  Admin fills the form and submit
    Then Admin should be able to see the New User on Users page
    And  the user logged out
    And the user login again as newUser
    Then the user should be on the dashboardPage
    Then the user logged out

