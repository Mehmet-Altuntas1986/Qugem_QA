@w
Feature: Admin should be able to delete a Role

	Background:
		#@PRECOND_QUGEM-32
		Given the user logged in as 'admin'
		When admin navigates to 'adminOptions'

	#Accept. C.:
	#Admin should be able to delete a Company if; there is not any employee working under this company. 
	#The system should ask a control Question like “Are you sure?“
	#Deleted Company disappears from the list directly without refresh

	@TEST_QUGEM-189
	Scenario: Admin should be able to delete a Role
		And Admin deletes the last created ROLE
		Then The ROLE Value needs to be no more listed on OptionsPage
