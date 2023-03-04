Feature: Admin should be able to add a new Employee

	Background:
		#@PRECOND_QUGEM-61
		Given the user logged in as 'admin'

	#Admin should be able to add a new Employee
	@TEST_QUGEM-60 @addEmployee
	Scenario: Admin should be able to add a new Employee
		When admin navigates to 'employee'
		And the user clicks on the  create new employee button
		And the user fills the form for newEmployee
		Then the user should see new record on EmployeesPage
