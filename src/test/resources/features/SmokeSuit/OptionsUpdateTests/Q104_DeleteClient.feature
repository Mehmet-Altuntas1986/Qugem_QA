Feature: Admin should be able to delete a Client
	Background:
		#@PRECOND_QUGEM-32
		Given the user logged in as 'admin'
		When admin navigates to 'adminOptions'

	#Accep. C.:
	Scenario: Admin should be able to delete a Client
		And admin deletes 'TestClient' Client
		Then New Client Value needs to be no more listed on OptionsPage
