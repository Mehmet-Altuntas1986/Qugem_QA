Feature: Admin should be able to assign a Vehicle to Drivers_Automation

  Background:
		#@PRECOND_QUGEM-26
    Given the user logged in as 'admin'

	#Admin should be able to assign a free (not in Use or repair) Auto to Employee(s)
  @TEST_QUGEM-168 @assignVehicle
  Scenario Outline: Admin should be able to assign a Vehicle to Drivers_Automation
    Given admin navigates to 'auto'
    And   Click the usage button of "<Plate>"
    And   Click the add button
    And   Select "<firstDriver>"
    And   Select if not null "<secondDriver>"
    And   Update date"<startDate>"
    And   Update if its not null "<startKM>"
    And   Click Submit Button
    Then  Assignee info should be listed on PopUP
    Then  Assignee info should be listed on Table
    And   admin navigates to 'auto'
    Then  Vehicle with "<Plate>" should be InUse
    Then  Assigned Drivers should be listed on table

    Examples:
      | Plate         | firstDriver  | secondDriver | startDate | startKM |
      | TEST Q168 001 | Driver1 Q168 |              |           |         |
      | TEST Q168 002 | Driver2 Q168 | Driver3 Q168 |           |         |



