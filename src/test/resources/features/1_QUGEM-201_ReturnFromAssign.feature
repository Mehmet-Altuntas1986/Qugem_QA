Feature: Admin should be able to Reassign A Vehicle (close assign)

	Background:
		#@PRECOND_QUGEM-26
		Given the user logged in as 'admin'

	#The vehicle is taken back from the driver.
	#The returned vehicle must be returned with higher mileage and the Distance must be calculated.
	#The last record should appear closed in the vehicle's usage list.
	#The returned vehicle must have Status (Status) → Bosta (Leehrlauf) in the Vehicles list.
	@TEST_QUGEM-201 @auto
	Scenario: Admin should be able to Reassign A Vehicle (close assign)
			Given admin navigates to 'auto'
		    And   Click the usage button of 'LB EY 580' to end the usage
		    And   Click the last records Edit Button
		    And   Update endDate
		    And   Update endKM
		    And   Click to Submit Button
		    Then  Return info should be listed on PopUP with Distance
			And   Close usage dialog
		    Then  The Vehicles status should be Free in Auto page for 'LB EY 580'
