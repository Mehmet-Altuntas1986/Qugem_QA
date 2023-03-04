@wip
Feature: Admin should be able to delete a Company

	Background:
		Given the user logged in as 'admin'
		When admin navigates to 'adminOptions'

	#Accept. C.:
	#
	#Admin should be able to delete a Company if; there is not any employee working under this company. 
	#The system should ask a control Question like “Are you sure?“
	#
	#Deleted Company disappears from the list directly without refresh
	Scenario: Admin should be able to delete a Company
		And  Admin deletes the last created Company
		Then The Company Value needs to be no more listed on OptionsPage
