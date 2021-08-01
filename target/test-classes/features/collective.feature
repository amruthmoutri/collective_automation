@sanity
Feature: As a user I want to test homepage
  so that I can book a site to live

  Background:
    Given I am on homepage

  Scenario: Verify header navigation items
    Then Verify navigation items are visible
      | Our Locations |
      | About Us      |
      | The Journal   |

  Scenario: Verify "book now" button is displayed
    Then I verify button book now is visible

  Scenario: Click "Our location" header and verify locations
    When I click on navigation header "Our location"
    Then I expect these locations
      | Canary Wharf  |
      | Paper Factory |
      | Harrow        |
      | Old Oak       |


  Scenario Outline: Verify "view" button is displayed in "<location Name>"
    When I click on navigation header "Our location"
    And I verify view button is displayed in location "<location Name>"
    Examples:
      | location Name |
      | old-oak       |
      | canary-wharf  |
      | paper-factory |
      | harrow        |

  Scenario: Click on "view" button in "<locations>"
    When I click on navigation header "Our location"
     And I click on location Old Oak
    Then I am on Old Oak page
    When I click on navigation header "Our location"
     And I click on location Canary Wharf
    Then I am on Canary Wharf page
    When I click on navigation header "Our location"
    Then I click on location Paper Factory
     And I am on Paper Factory page
    When I click on navigation header "Our location"
     And I click on location Harrow
    Then I am on Harrow page


  Scenario: Click book now and fill out few nights form
    When I click on book now button
    Then I enter fields in the form
      | Location       | Canary Wharf |
      | Arrival Date   | 1/JUL 2021   |
      | Departure Date | 30/AUG 2021  |

  Scenario: Click stay for a few night button on homepage image to verify the form opens
    When I click on button "Stay for a few nights"
    Then I verify form "Stay for a few nights" is active









