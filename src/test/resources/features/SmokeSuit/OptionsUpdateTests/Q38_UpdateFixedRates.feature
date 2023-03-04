Feature: Admin should be able to update Fixed Rates

  Background:
		#@PRECOND_QUGEM-32
    Given the user logged in as 'admin'
    When admin navigates to 'adminOptions'

	#AC:
  @TEST_QUGEM-38 @adminFeatures @fixedRatesUpdate @optionsUpdate
  Scenario: Admin should be able to update Fixed Rates
    And  admin changes FixedRates
    Then New FixedRates Value needs to be listed on OptionsPage
