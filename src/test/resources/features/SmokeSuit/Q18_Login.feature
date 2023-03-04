Feature: Admin should be able to login with true credentials

	#Acceptance Criteria :
  @TEST_QUGEM-18 @login
  Scenario: Admin should be able to login with true credentials
    Given the user is on the login page
    When the user logged in as 'admin'
    Then the user should be on the dashboardPage
