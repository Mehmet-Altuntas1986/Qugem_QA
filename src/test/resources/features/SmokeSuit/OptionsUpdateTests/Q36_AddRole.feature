Feature: Admin should be able to add a Role

  Background:
		#@PRECOND_QUGEM-32
    Given the user logged in as 'admin'
    When admin navigates to 'adminOptions'


	#A.C.:
  Scenario: Admin should be able to update Role Options
    And admin adds new Role
    Then New Role Value needs to be listed on OptionsPage
