Feature:  Admin should be able to add a Client
	Background:
		#@PRECOND_QUGEM-32
		Given the user logged in as 'admin'
		When admin navigates to 'adminOptions'

	#Accep. C.:
	Scenario:  Admin should be able to update Client Options
		And admin adds new Client
		Then New Client Value needs to be listed on OptionsPage
