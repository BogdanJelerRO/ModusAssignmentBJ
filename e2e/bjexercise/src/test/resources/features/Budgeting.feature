Feature: Calculate The Working Balance

  In order to calculate the working balance
  As an user of the budget app
  I want to be able to introduce the inflow and outflow values so
  I can keep track of my expenses and see reports

  Background:
    Given I am on the budget page

  Scenario: Insert outflow value
    When I delete all entries from the table
    When I select the Category "Taxes"
    And I complete the Description with "Tax value inserted"
    And I enter the value "400"
    Then I should be able to add the entry in the table
    And The Working balance should be "400"


# Comment this scenario for the tests to pass. (Scenario created in the scope of the assignment)
# The steps will fail because the Update button not available for the first row when table is empty and new entry is added.
# Result is that the new value amount will not be inserted and updated according to the scenario
  Scenario: Update first row with another outflow value
    When I change the value of the first amount value with "500"
    Then I should see the new value "500" in the table
    And The Working balance should be "500"
