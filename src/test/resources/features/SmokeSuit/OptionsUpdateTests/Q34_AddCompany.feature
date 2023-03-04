@wip
Feature: Admin should be able to add a Company

	Background:
		#@PRECOND_QUGEM-32
		Given the user logged in as 'admin'
		When admin navigates to 'adminOptions'

	#AC:
	Scenario: Admin should be able to update Company Options
		And admin adds new Company
		Then New Company Value needs to be listed on OptionsPage