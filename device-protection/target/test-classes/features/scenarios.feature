Feature: Requirements

	@FirReq
	Scenario: Ensure dropdown price list is visible
		Given I am on the registration page
		When I select the dropdown price list and wait
	
	@SecReq
  Scenario: Randomly select a device purchase price
    Given Open the registration page
    When I select a random purchase price from the dropdown
    Then the selected price should be displayed correctly
    
	@SecReq
  Scenario: Select a specific device purchase price
    Given Open the registration page
    When I select the purchase price "THB 2,000 - 6,000" from the dropdown
    Then the selected price should be displayed correctly
   
  @ThiReq
  Scenario: Randomly select a device purchase price
    Given Open the registration page
    When I select a random purchase price from the dropdown
    And the selected price should be displayed correctly
    Then I click the Select button
