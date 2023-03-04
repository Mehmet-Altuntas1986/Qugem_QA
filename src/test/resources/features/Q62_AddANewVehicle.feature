@addAuto
Feature: Admin should be able to create a new car

  Background: user logins as {admin}
    Given the user logged in as 'admin'

  Scenario: Create a new car
    And the user navigates to "auto"
    And the user clicks add auto button
    And the user fills the form
    When the user clicks the save button
    Then the user should be able to see new auto information




