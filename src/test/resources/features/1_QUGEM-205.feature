Feature: Admin should be able to open a Repair

	Background:
		#@PRECOND_QUGEM-26
		Given the user logged in as 'admin'

	#Admin */ *User *should be able to create repair info to a Vehicle
	#
	#* Repair Openbox info should be in Selected App Language
	#* The plate input should be Disable
	#* Start Date Should be entered DD/MM/YEAR (MANDATORY)
	#* End Date should be disable
	#* Current Km should come automatically but it can be changed
	#* Preis should be disable
	#* Details (optional)
	#* Submit button should save the repair info to Database and list the record in Repair History
	#* If the vehicle has already a repair History new record should be at the last line of the list
	#* There should be Edit and Delete Buttons for Open Repair 
	#* After closing the Repair Popup Status gonna be updated (Free / In Repair)
	@TEST_QUGEM-205 @repair
	Scenario: Admin should be able to open a Repair
		When  admin navigates to 'auto'
		And   Click the Repair button of 'plate' to end the usage
		And   Click the addButton
		And   Update startDate
		And   Update KM
		And   Enter Repair details
		And   Click to Submit Button
		Then  Repair info should be listed on PopUP with Edit Delete
		And   Close Repair Menu
		Then  The Vehicles status should be InRepair in Auto page
